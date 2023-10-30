package com.chattingservice.api.chatting.controller;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.domain.chatting.service.ChatMessageService;
import com.chattingservice.domain.room.service.ChatRoomService;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import com.chattingservice.global.kafka.KafkaProducer;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Chatting API", description = "채팅 메시지 API")
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/v1/chatting")
public class ChatMessageController {
    private final KafkaProducer producers;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;


    @Operation(summary = "메시지 전송")
    @PostMapping(value = "/message", consumes = "application/json", produces = "application/json")
    public ApiResponseEntity<String> sendMessage(@Valid @RequestBody ChatMessageDto chatMessageDto) {
        if (!chatRoomService.existsRoom(chatMessageDto.getRoom_id())) {
            throw new BusinessException(ErrorCode.ROOM_NOT_FOUND_ERROR);
        }
        ChatMessageDto savedMessage = chatMessageService.saveChatMessage(chatMessageDto);
        producers.sendMessage(savedMessage);
        return ApiResponseEntity.ok("success");
    }

    @Operation(summary = "채팅방 새 메시지 받기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 새 메시지 조회 성공")})
    @GetMapping("/new-message/{roomid}/{readMsgId}")
    public ApiResponseEntity<List<ChatMessageDto>> newMessagesAtRoom(@Parameter(description = "채팅방 id") @PathVariable(value = "roomid") String roomId,
                                                                     @Parameter(description = "메세지 id") @PathVariable String readMsgId) {

        List<ChatMessageDto> chatMessageDtos = chatMessageService.getNewMessages(roomId, readMsgId);
        return ApiResponseEntity.ok(chatMessageDtos);
    }

    @Operation(summary = "과거채팅보기(해당 채팅방내 전체 메시지 가져오기)", description = "내림차순으로 해당 채팅방의 전체 메세지를 조회합니다.")
    @GetMapping("/history-message/{roomid}")
    public ApiResponseEntity<List<ChatMessageDto>> allMessagesAtRoom(@Parameter(description = "채팅방 id") @PathVariable(value = "roomid") String roomId) {
        List<ChatMessageDto> chatMessageDtos = chatMessageService.getAllMessagesAtRoom(roomId);
        return ApiResponseEntity.ok(chatMessageDtos);
    }

    @Operation(summary = "채팅 Pagination", description = "내림차순으로 해당 채팅방 Pagination, 사이즈 N = 50 고정")
    @GetMapping("/history")
    public ApiResponseEntity<Page<ChatMessageDto> > chatMessagePagination(
            @Parameter(description = "채팅방 id") @RequestParam(name = "roomid") String roomId,
            @Parameter(description = "첫 페이지는 0부터 시작") @RequestParam(name = "page") int page) {

        Page<ChatMessageDto> chatMessageDtos = chatMessageService.chatMessagePagination(roomId, page);
        return ApiResponseEntity.ok(chatMessageDtos);
    }
}

