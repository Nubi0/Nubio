package com.chattingservice.api.chattingroom.controller;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomEnterReq;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomOutReq;
import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import com.chattingservice.api.chattingroom.service.ChattingRoomInfoService;
import com.chattingservice.global.resolver.memberInfo.MemberInfo;
import com.chattingservice.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ChattingRoom API", description = "채팅방 api")
@RestController
@RequestMapping("/v1/chatting/room")
@RequiredArgsConstructor
public class ChattingRoomController {

    private final ChattingRoomInfoService chattingRoomInfoService;

    @Operation(summary = "그룹 채팅방 입장", description = "chatting/v1/chatting/room/enter\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/enter")
    public ApiResponseEntity<ChattingRoomResp> enterGroupRoom(@MemberInfo MemberInfoDto memberInfoDto,
            @RequestBody ChattingRoomEnterReq chattingRoomEnterReq) {
        chattingRoomEnterReq.setMemberId(memberInfoDto.getMemberId());
        ChattingRoomResp chattingRoomResp = chattingRoomInfoService.enterGroupRoom(chattingRoomEnterReq);
        return ApiResponseEntity.ok(chattingRoomResp);
    }

    @Operation(summary = "그룹 채팅방 나가기", description = "chatting/v1/chatting/room/out\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/out")
    public ApiResponseEntity<ChattingRoomResp> outOfGroupRoom(@MemberInfo MemberInfoDto memberInfoDto,
                                                              @RequestBody ChattingRoomOutReq chattingRoomOutReq) {
        chattingRoomOutReq.setMemberId(memberInfoDto.getMemberId());
        ChattingRoomResp chattingRoomResp = chattingRoomInfoService.outOfGroupRoom(chattingRoomOutReq);
        return ApiResponseEntity.ok(chattingRoomResp);
    }

}
