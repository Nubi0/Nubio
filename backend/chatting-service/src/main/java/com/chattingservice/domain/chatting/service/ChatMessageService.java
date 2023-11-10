package com.chattingservice.domain.chatting.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.chattingservice.api.chatting.dto.request.ChatMessagePageDto;
import com.chattingservice.domain.chatting.dto.request.ChatFileDto;
import com.chattingservice.domain.chatting.entity.MessageCollection;
import com.chattingservice.domain.chatting.mongo.ChatMessageRepository;
import com.chattingservice.domain.chattingroom.entity.ChattingRoom;
import com.chattingservice.domain.participant.ParticipantService;
import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.participant.repository.ParticipantRepository;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.chattingservice.global.kafka.dto.response.ChatMessageResp;
import com.chattingservice.global.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final AmazonS3Client amazonS3Client;
    private final ParticipantService participantService;
    private static final int SIZE = 50;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.s3.bucket2}")
    private String resizeBucketName;
    private static final int FILE_AMOUNT_LIMIT = 10;

    @Transactional
    public ChatMessageResp saveChatMessage(ChatMessageDto chatMessageDto) {
        Participant participant = validateParticipate(chatMessageDto.getSender_id(), Long.parseLong(chatMessageDto.getRoom_id()));
        MessageCollection messageCollection = chatMessageRepository.save(MessageCollection.builder()
                .type(chatMessageDto.getMessage_type())
                .roomId(chatMessageDto.getRoom_id())
                .senderId(chatMessageDto.getSender_id())
                .content(chatMessageDto.getContent())
                .createdAt(LocalDateTime.now())
                .nickname(participant.getNickname().getValue())
                .build());


        if (chatMessageDto.getMessage_type().name().equals("IMG")) {
            uploadFiles("chatting", chatMessageDto.getFiles(), messageCollection);
        }

        ChatMessageResp chatMessageResp = ChatMessageResp.from(messageCollection);
        return chatMessageResp;

    }

    private Participant validateParticipate(String memberId, Long roomId) {

        Participant participant = participantService.findByMemberIdAndChattingRoomId(memberId, roomId).orElseThrow(
                () -> new BusinessException(ErrorCode.PARTICIPANT_NOT_EXIST)
        );
        return participant;
    }

    private boolean validateFileExists(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_FILE);
        }
        return true;
    }

    private void validateFileSize(List<MultipartFile> files) {
        if (files.size() > FILE_AMOUNT_LIMIT) {
            throw new BusinessException(ErrorCode.FILE_AMOUNTS_LIMIT);
        }
    }

    @Transactional
    public void uploadFiles(String category,
                            List<MultipartFile> files,
                            MessageCollection messageCollection) {
        List<String> originImgUrlList = new ArrayList<>();
        List<String> resizeImgUrlList = new ArrayList<>();

        if (files != null) {
            List<MultipartFile> validMultipartFiles = files.stream()
                    .filter(this::validateFileExists)
                    .collect(Collectors.toList());

            validateFileSize(validMultipartFiles);

            LocalDateTime now = LocalDateTime.now();

            List<ChatFileDto> responseDtos = new ArrayList<>();
            for (MultipartFile file : validMultipartFiles) {
                String fileName = FileUtils.buildFileName(category, messageCollection.get_id(), file.getOriginalFilename(), now);

                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(file.getSize());
                objectMetadata.setContentType(file.getContentType());

                try (InputStream inputStream = file.getInputStream()) {
                    amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));

                    String url = amazonS3Client.getUrl(bucketName, fileName).toString();
                    String resizeUrl = amazonS3Client.getUrl(resizeBucketName, fileName).toString();
                    ChatFileDto fileUploadResponseDto = new ChatFileDto(url);
                    responseDtos.add(fileUploadResponseDto);

                    originImgUrlList.add(url);
                    resizeImgUrlList.add(resizeUrl);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        messageCollection.updateOriginImgUrl(originImgUrlList);
        messageCollection.updateResizeImgUrl(resizeImgUrlList);
        chatMessageRepository.save(messageCollection);
    }

    public List<ChatMessageResp> getNewMessages(String roomId, String readMsgId) {
        List<MessageCollection> messageCollections = chatMessageRepository.getNewMessages(roomId, readMsgId);
        return messageCollections.stream().map(m -> ChatMessageResp.from(m)).collect(Collectors.toList());
    }

    public List<ChatMessageResp> getAllMessagesAtRoom(String roomId) {
        return chatMessageRepository.getAllMessagesAtRoom(roomId).stream().map(mc -> ChatMessageResp.from(mc)).collect(Collectors.toList());
    }

    public ChatMessagePageDto chatMessagePagination(String roomId, Pageable page) {
        Page<MessageCollection> messageCollectionPage = chatMessageRepository.findByRoomIdWithPagingAndFiltering(roomId, page);
        ChatMessagePageDto.from(messageCollectionPage);
        return ChatMessagePageDto.from(messageCollectionPage);
    }


    public void deleteChat(String id) {
        chatMessageRepository.deleteById(id);
    }

}
