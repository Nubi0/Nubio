package com.chattingservice.domain.chatting.service;

import com.chattingservice.domain.chatting.entity.MessageCollection;
import com.chattingservice.domain.chatting.mongo.ChatMessageRepository;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private static final int SIZE = 50;

    public ChatMessageDto saveChatMessage(ChatMessageDto chatMessageDto){

        MessageCollection messageCollection = chatMessageRepository.save(MessageCollection.builder()
                .type(chatMessageDto.getMessage_type())
                .roomId(chatMessageDto.getRoom_id())
                .senderId(chatMessageDto.getSender_id())
                .content(chatMessageDto.getContent())
                .createdAt(LocalDateTime.now())
                .build());

        return ChatMessageDto.from(messageCollection);
    }

    public List<ChatMessageDto> getNewMessages(String roomId, String readMsgId){
        List<MessageCollection> messageCollections = chatMessageRepository.getNewMessages(roomId, readMsgId);
        return messageCollections.stream().map(m -> ChatMessageDto.from(m)).collect(Collectors.toList());
    }

    public List<ChatMessageDto> getAllMessagesAtRoom(String roomId) {
        return chatMessageRepository.getAllMessagesAtRoom(roomId).stream().map(mc -> ChatMessageDto.from(mc)).collect(Collectors.toList());
    }

    public Page<ChatMessageDto> chatMessagePagination(String roomId, int page){
        Page<MessageCollection> messageCollectionPage = chatMessageRepository.findByRoomIdWithPagingAndFiltering(roomId, page, SIZE);
        Page<ChatMessageDto> chatMessageDtoPage = messageCollectionPage.map(messageCollection -> ChatMessageDto.from(messageCollection));

        return chatMessageDtoPage;
    }


    public void deleteChat(String id){
        chatMessageRepository.deleteById(id);
    }

}
