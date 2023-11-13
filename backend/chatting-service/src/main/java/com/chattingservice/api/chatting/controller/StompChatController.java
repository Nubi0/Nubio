package com.chattingservice.api.chatting.controller;


import com.chattingservice.domain.chatting.service.ChatMessageService;
import com.chattingservice.domain.room.service.ChatRoomService;
import com.chattingservice.external.authentication.client.MemberInfoClient;
import com.chattingservice.external.authentication.dto.MemberInfoRes;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import com.chattingservice.global.kafka.KafkaProducer;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.chattingservice.global.kafka.dto.response.ChatMessageResp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StompChatController {

    private final KafkaProducer producers;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

//    @MessageMapping("/chatting/pub")
//    @Operation(summary = "웹소켓메시지 전송")
//    public ChatMessageDto sendSocketMessage2(@Valid @RequestBody ChatMessageDto chatMessageDto) {
//
//        if (!chatRoomService.existsRoom(chatMessageDto.getRoom_id())) {
//            throw new BusinessException(ErrorCode.ROOM_NOT_FOUND_ERROR);
//        }
//
//        ChatMessageDto savedMessage = chatMessageService.saveChatMessage(chatMessageDto);
//        producers.sendMessage(savedMessage);
//        return savedMessage;
//    }

    @MessageMapping("/room/enter/{room_id}")
//    @SendTo("/chatting/topic/room/{room_id}")
    @Operation(summary = "채팅방 입장")
    public void sendEnterMessage(@Valid @RequestBody ChatMessageDto chatMessageDto) {

        if (!chatRoomService.existsRoom(chatMessageDto.getRoom_id())) {
            throw new BusinessException(ErrorCode.ROOM_NOT_FOUND_ERROR);
        }

        URI uri = UriComponentsBuilder
                .fromUriString("http://ec2-13-124-94-38.ap-northeast-2.compute.amazonaws.com:8080")
                .path("/v1/member/me/"+chatMessageDto.getSender_id())
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberInfoRes> response = restTemplate.getForEntity(uri, MemberInfoRes.class);


        chatMessageDto.setContent(response.getBody().getData().getNickname()+"님이 채팅방에 참여하였습니다.");
        ChatMessageResp chatMessageResp = chatMessageService.saveChatMessage(chatMessageDto);
        producers.sendMessage(chatMessageResp);
//        return chatMessageResp;
    }

    @MessageMapping("/chatting/pub")
//    @MessageMapping("/room/{room_id}")
//    @MessageMapping("/room")
//    @SendTo("/chatting/topic/room/{room_id}")
    @Operation(summary = "웹소켓메시지 전송")
    public void sendSocketMessage(@Valid @RequestBody ChatMessageDto chatMessageDto) {

        if (!chatRoomService.existsRoom(chatMessageDto.getRoom_id())) {
            throw new BusinessException(ErrorCode.ROOM_NOT_FOUND_ERROR);
        }

        ChatMessageResp chatMessageResp = chatMessageService.saveChatMessage(chatMessageDto);
        producers.sendMessage(chatMessageResp);
//        return chatMessageResp;
    }

}
