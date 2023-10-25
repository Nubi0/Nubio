package com.chattingservice.global.kafka;

import com.chattingservice.domain.chatting.service.ChatMessageService;
import com.chattingservice.domain.room.service.ChatRoomService;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.chattingservice.global.kafka.dto.MessageType;
import com.chattingservice.global.kafka.dto.RoomMessageDto;
import com.chattingservice.global.kafka.dto.request.RespRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

// 토픽에 메시지전송(이벤트 발행)
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    //KafkaProducerConfig 클래스에서 설정한 KafkaTemplate
    private final KafkaTemplate<String, ChatMessageDto> kafkaTemplate;
    @Value("${kafka.topic.chat-name}")
    private String topicChatName;

    //KafkaProducerConfig 클래스에서 설정한 KafkaTemplate
    private final KafkaTemplate<String, RoomMessageDto> roomKafkaTemplate;
    @Value("${kafka.topic.room-name}")
    private String topicRoomName;
    private ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;


    public void sendMessage(ChatMessageDto chatMessageDto){
        if(chatMessageDto.getMessage_type()== MessageType.FIRST){
            // 채팅방이 생성될 때 채팅방 정보를 전달할 때 호출
            RespRoomDto respRoomDto = chatRoomService.getChatRoomInfo(chatMessageDto.getRoom_id()); // 채팅방 무조건 있다고 신뢰
            List<String> receivers = respRoomDto.getMembers().stream().map(m -> m.getUserId()).collect(Collectors.toList());
            receivers.remove(chatMessageDto.getSender_id());
            sendRoomMessage(RoomMessageDto.builder()
                    .receivers(receivers)
                    .respRoomDto(respRoomDto)
                    .build());
        }else {
            // 채팅메시지를 전달
            CompletableFuture<SendResult<String, ChatMessageDto>> completableFuture = kafkaTemplate.send(topicChatName, chatMessageDto);

            completableFuture.thenAccept(result -> {
                if (result != null) {
                    // 성공적으로 메시지가 보내진 경우
                    log.info("채팅방: {}, 보낸사람: {}, 메시지: {}", chatMessageDto.getRoom_id(), chatMessageDto.getSender_id(), chatMessageDto.getContent());
                } else {
                    // 메시지 보내기 실패한 경우
                    log.error("메시지 보내기 실패: " + chatMessageDto.getContent());
                    chatMessageService.deleteChat(chatMessageDto.getMessage_id());
                    log.info("메시지 삭제: {}", chatMessageDto.getMessage_id());
                }
            });
        }
        log.info("url:{}","/chatting/topic/room/"+chatMessageDto.getRoom_id());

    }

    // 채팅방이 생성될 때 채팅방 정보를 전달할 때 호출하는 메소드입니다.
    public void sendRoomMessage(RoomMessageDto roomMessageDto){
        CompletableFuture<SendResult<String, RoomMessageDto>> completableFuture = roomKafkaTemplate.send(topicRoomName, roomMessageDto);

        completableFuture.thenAccept(result -> {
            if (result != null) {
                // 성공적으로 메시지가 보내진 경우
                log.info("Sent message=[" + roomMessageDto.getRespRoomDto().getRoom_id() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                // 메시지 보내기 실패한 경우
                log.info("메시지 보내기 실패::" + roomMessageDto.getRespRoomDto().getRoom_id() );

            }
        });

    }
}
