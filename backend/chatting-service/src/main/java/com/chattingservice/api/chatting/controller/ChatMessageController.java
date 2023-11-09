package com.chattingservice.api.chatting.controller;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.api.chatting.dto.request.ChatMessagePageDto;
import com.chattingservice.api.chattingroom.service.ChattingRoomInfoService;
import com.chattingservice.domain.chatting.service.ChatMessageService;
import com.chattingservice.domain.room.service.ChatRoomService;
import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.BusinessException;
import com.chattingservice.global.kafka.KafkaProducer;
import com.chattingservice.global.kafka.dto.request.ChatMessageDto;
import com.chattingservice.global.kafka.dto.response.ChatMessageResp;
import com.chattingservice.global.resolver.memberInfo.MemberInfo;
import com.chattingservice.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Chatting API", description = "채팅 메시지 API")
@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/v1/chatting")
public class ChatMessageController {
    private final KafkaProducer producers;
    private final ChatMessageService chatMessageService;
    private final ChattingRoomInfoService chattingRoomInfoService;


    @Operation(summary = "메시지 전송")
    @PostMapping(value = "/message", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<String> sendMessage(@MemberInfo MemberInfoDto memberInfoDto,
                                                 @Valid  @RequestPart("chatting") ChatMessageDto chatMessageDto
    , @RequestPart(value = "file", required = false) List<MultipartFile> files) {
        chatMessageDto.setSender_id(memberInfoDto.getMemberId());
        chatMessageDto.setFiles(files);
        ChatMessageResp savedMessage = chatMessageService.saveChatMessage(chatMessageDto);
        producers.sendMessage(savedMessage);
        return ApiResponseEntity.ok("success");
    }

    @Operation(summary = "채팅방 새 메시지 받기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "채팅방 새 메시지 조회 성공")})
    @GetMapping("/new-message/{roomid}/{readMsgId}")
    public ApiResponseEntity<List<ChatMessageResp>> newMessagesAtRoom(@Parameter(description = "채팅방 id") @PathVariable(value = "roomid") String roomId,
                                                                     @Parameter(description = "메세지 id") @PathVariable String readMsgId) {

        List<ChatMessageResp> chatMessageDtos = chatMessageService.getNewMessages(roomId, readMsgId);
        return ApiResponseEntity.ok(chatMessageDtos);
    }

    @Operation(summary = "과거채팅보기(해당 채팅방내 전체 메시지 가져오기)", description = "내림차순으로 해당 채팅방의 전체 메세지를 조회합니다.")
    @GetMapping("/history-message/{roomid}")
    public ApiResponseEntity<List<ChatMessageResp>> allMessagesAtRoom(@Parameter(description = "채팅방 id") @PathVariable(value = "roomid") String roomId) {
        List<ChatMessageResp> chatMessageDtos = chatMessageService.getAllMessagesAtRoom(roomId);
        return ApiResponseEntity.ok(chatMessageDtos);
    }

    @Operation(summary = "채팅 Pagination", description = "내림차순으로 해당 채팅방 Pagination")
    @GetMapping("/history")
    public ApiResponseEntity<ChatMessagePageDto> chatMessagePagination(
            @Parameter(description = "채팅방 id") @RequestParam(name = "roomid") String roomId,
            @PageableDefault(size = 50,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable) {

        ChatMessagePageDto chatMessagePageDto = chatMessageService.chatMessagePagination(roomId, pageable);
        return ApiResponseEntity.ok(chatMessagePageDto);
    }
}

