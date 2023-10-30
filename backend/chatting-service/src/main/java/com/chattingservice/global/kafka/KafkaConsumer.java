package com.chattingservice.global.kafka;

import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.chattingservice.global.kafka.dto.request.RoomMessageDto;
import com.chattingservice.global.kafka.dto.request.RespRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {
    // 토픽에 발행된 이벤트를 가져와 처리할 Consumer입니다.
    // Producers에서 전달된 메시지는 Consumers에서 토픽에 따라 받습니다.

    private final SimpMessagingTemplate template;

    @KafkaListener(groupId = "${spring.kafka.chat-consumer.group-id}" ,topics="${kafka.topic.chat-name}")
    public void listenChat(ChatMessageDto chatMessageDto){
        template.convertAndSend("/chatting/topic/room/"+chatMessageDto.getRoom_id(), chatMessageDto);
    }

    @KafkaListener(groupId = "${spring.kafka.room-consumer.group-id}",topics = "${kafka.topic.room-name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupCreation(RoomMessageDto roomMessageDto){
        RespRoomDto respRoomDto = roomMessageDto.getRespRoomDto();
        for(String userId : roomMessageDto.getReceivers()){
            template.convertAndSend("/chatting/topic/new-room/"+userId,respRoomDto);
        }
    }
}