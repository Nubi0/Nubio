package com.chattingservice.global.kafka;

import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import com.chattingservice.api.chattingroom.service.ChattingRoomInfoService;
import com.chattingservice.domain.chatting.service.ChatMessageService;
import com.chattingservice.domain.chattingroom.service.ChattingRoomService;
import com.chattingservice.domain.chatting.entity.constant.MessageType;
import com.chattingservice.global.kafka.dto.request.RoomMessageDto;
import com.chattingservice.global.kafka.dto.response.ChatMessageResp;
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
    private final KafkaTemplate<String, ChatMessageResp> kafkaTemplate;
    @Value("${kafka.topic.chat-name}")
    private String topicChatName;

    //KafkaProducerConfig 클래스에서 설정한 KafkaTemplate
    private final KafkaTemplate<String, RoomMessageDto> roomKafkaTemplate;
    @Value("${kafka.topic.room-name}")
    private String topicRoomName;
    private ChatMessageService chatMessageService;
    private final ChattingRoomService chattingRoomService;
    private final ChattingRoomInfoService chattingRoomInfoService;

    public void sendMessage(ChatMessageResp chatMessageDto){
        if(chatMessageDto.getMessage_type()== MessageType.ENTER){
            // 채팅방이 생성될 때 채팅방 정보를 전달할 때 호출

            ChattingRoomResp respRoomDto = chattingRoomInfoService.findById(chatMessageDto.getRoom_id());// 채팅방 무조건 있다고 신뢰
            List<String> receivers = respRoomDto.getMembers().stream().map(m -> m.getMemberId()).collect(Collectors.toList());
            receivers.remove(chatMessageDto.getSender_id());
            sendRoomMessage(RoomMessageDto.builder()
                    .members(receivers)
                    .respRoomDto(respRoomDto)
                    .build());
        }else {
            // 채팅메시지를 전달
            CompletableFuture<SendResult<String, ChatMessageResp>> completableFuture = kafkaTemplate.send(topicChatName, chatMessageDto);

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
                log.info("Sent message=[" + roomMessageDto.getRespRoomDto().getRoomId() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                // 메시지 보내기 실패한 경우
                log.info("메시지 보내기 실패::" + roomMessageDto.getRespRoomDto().getRoomId() );

            }
        });
    }
}
