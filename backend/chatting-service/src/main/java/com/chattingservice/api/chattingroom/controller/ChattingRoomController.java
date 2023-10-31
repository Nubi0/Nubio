package com.chattingservice.api.chattingroom.controller;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomEnterReq;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomOutReq;
import com.chattingservice.api.chattingroom.dto.request.ChattingRoomSearchReq;
import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import com.chattingservice.api.chattingroom.service.ChattingRoomInfoService;
import com.chattingservice.global.kafka.KafkaProducer;
import com.chattingservice.global.kafka.dto.request.RoomMessageDto;
import com.chattingservice.global.resolver.memberInfo.MemberInfo;
import com.chattingservice.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "ChattingRoom API", description = "채팅방 api")
@RestController
@RequestMapping("/v1/chatting/room")
@RequiredArgsConstructor
public class ChattingRoomController {

    private final ChattingRoomInfoService chattingRoomInfoService;
    private final KafkaProducer producers;


    @Operation(summary = "그룹 채팅방 입장", description = "chatting/v1/chatting/room/enter\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/enter")
    public ApiResponseEntity<ChattingRoomResp> enterGroupRoom(@MemberInfo MemberInfoDto memberInfoDto,
            @RequestBody ChattingRoomEnterReq chattingRoomEnterReq) {
        chattingRoomEnterReq.setMemberId(memberInfoDto.getMemberId());
        ChattingRoomResp chattingRoomResp = chattingRoomInfoService.enterGroupRoom(chattingRoomEnterReq);

        producers.sendRoomMessage(RoomMessageDto.builder()
                .receivers(chattingRoomResp.getMembers().stream().map(member-> member.getMemberId()).collect(Collectors.toList()))
                .respRoomDto(chattingRoomResp)
                .build());

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

        producers.sendRoomMessage(RoomMessageDto.builder()
                .receivers(chattingRoomResp.getMembers().stream().map(member-> member.getMemberId()).collect(Collectors.toList()))
                .respRoomDto(chattingRoomResp)
                .build());

        return ApiResponseEntity.ok(chattingRoomResp);
    }


    @Operation(summary = "나의 채팅방 리스트 조회", description = "chatting/v1/chatting/room/my-rooms\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/my-rooms")
    public ApiResponseEntity<List<ChattingRoomResp> > myChatRooms(@MemberInfo MemberInfoDto memberInfoDto) {
        List<ChattingRoomResp> myRoomsByMemberId = chattingRoomInfoService.findMyRoomsByMemberId(memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(myRoomsByMemberId);
    }

    @Operation(summary = "채팅방 리스트 조회", description = "chatting/v1/chatting/room/rooms\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/rooms")
    public ApiResponseEntity<List<ChattingRoomResp> > findAll(@MemberInfo MemberInfoDto memberInfoDto
                                                              , @RequestBody ChattingRoomSearchReq chattingRoomSearchReq
    ) {
        List<ChattingRoomResp> myRoomsByMemberId = chattingRoomInfoService.searchRegion(chattingRoomSearchReq);
        return ApiResponseEntity.ok(myRoomsByMemberId);
    }

}
