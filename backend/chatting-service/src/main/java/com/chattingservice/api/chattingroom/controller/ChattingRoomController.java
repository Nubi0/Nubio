package com.chattingservice.api.chattingroom.controller;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.api.chatting.client.dto.request.ChatRequestDto;
import com.chattingservice.api.chatting.dto.request.ChatMessagePageDto;
import com.chattingservice.api.chattingroom.dto.request.*;
import com.chattingservice.api.chattingroom.dto.response.ChattingRoomEnterWithProfileResp;
import com.chattingservice.api.chattingroom.dto.response.ChattingRoomResp;
import com.chattingservice.api.chattingroom.service.ChattingRoomInfoService;
import com.chattingservice.api.participant.dto.response.ParticipantRes;
import com.chattingservice.api.participant.service.ParticipantInfoService;
import com.chattingservice.domain.chatting.service.ChatMessageService;
import com.chattingservice.global.kafka.KafkaProducer;
import com.chattingservice.global.kafka.dto.request.RoomMessageDto;
import com.chattingservice.global.resolver.memberInfo.MemberInfo;
import com.chattingservice.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "ChattingRoom API", description = "채팅방 api")
@RestController
@RequestMapping("/v1/chatting/room")
@RequiredArgsConstructor
public class ChattingRoomController {

    private final ChattingRoomInfoService chattingRoomInfoService;
    private final KafkaProducer producers;
    private final ParticipantInfoService participantInfoService;
    private final ChatMessageService chatMessageService;


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
                .receivers(chattingRoomResp.getMembers().stream().map(member -> member.getMemberId()).collect(Collectors.toList()))
                .respRoomDto(chattingRoomResp)
                .build());

        return ApiResponseEntity.ok(chattingRoomResp);
    }

    @Operation(summary = "프로필로 그룹 채팅방 입장", description = "chatting/v1/chatting/room/enter/profile\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/enter/profile")
    public ApiResponseEntity<ChattingRoomEnterWithProfileResp> enterGroupRoomWithProfile(@MemberInfo MemberInfoDto memberInfoDto,
                                                                                         @RequestBody ChattingRoomEnterWithProfileReq chattingRoomEnterWithProfileReq,
                                                                                         @PageableDefault(size = 50,
                                                                                                 sort = "createdAt",
                                                                                                 direction = Sort.Direction.DESC) Pageable pageable
    ) {

        ChattingRoomResp chattingRoomResp = chattingRoomInfoService.enterGroupRoomWithProfile(memberInfoDto.getMemberId()
                , chattingRoomEnterWithProfileReq.getRoomId()
                , chattingRoomEnterWithProfileReq.getNickName());

        producers.sendRoomMessage(RoomMessageDto.builder()
                .receivers(chattingRoomResp.getMembers().stream().map(member -> member.getMemberId()).collect(Collectors.toList()))
                .respRoomDto(chattingRoomResp)
                .build());

        ChatMessagePageDto chatMessagePageDto = chatMessageService.chatMessagePagination(String.valueOf(chattingRoomEnterWithProfileReq.getRoomId()), pageable);
        ChattingRoomEnterWithProfileResp chattingRoomEnterWithProfileResp = ChattingRoomEnterWithProfileResp.of(chattingRoomResp, chatMessagePageDto);
        return ApiResponseEntity.ok(chattingRoomEnterWithProfileResp);
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
                .receivers(chattingRoomResp.getMembers().stream().map(member -> member.getMemberId()).collect(Collectors.toList()))
                .respRoomDto(chattingRoomResp)
                .build());

        return ApiResponseEntity.ok(chattingRoomResp);
    }


    @Operation(summary = "나의 채팅방 리스트 조회", description = "chatting/v1/chatting/room/my-rooms\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/my-rooms")
    public ApiResponseEntity<List<ChattingRoomResp>> myChatRooms(@MemberInfo MemberInfoDto memberInfoDto) {
        List<ChattingRoomResp> myRoomsByMemberId = chattingRoomInfoService.findMyRoomsByMemberId(memberInfoDto.getMemberId());
        return ApiResponseEntity.ok(myRoomsByMemberId);
    }

    @Operation(summary = "채팅방 리스트 조회", description = "chatting/v1/chatting/room/rooms\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/rooms")
    public ApiResponseEntity<List<ChattingRoomResp>> findAll(@MemberInfo MemberInfoDto memberInfoDto
            , @RequestBody ChattingRoomSearchReq chattingRoomSearchReq
    ) {
        List<ChattingRoomResp> myRoomsByMemberId = chattingRoomInfoService.searchRegion(chattingRoomSearchReq);
        return ApiResponseEntity.ok(myRoomsByMemberId);
    }


    @Operation(summary = "현재위치 기반으로 채팅방 조회", description = "chatting/v1/chatting/room/location\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/location")
    public ApiResponseEntity<FindLocationReq> findLocation(@MemberInfo MemberInfoDto memberInfoDto
            , @RequestBody ChatRequestDto chatRequestDto
    ) {
        FindLocationReq location = chattingRoomInfoService.findLocation(chatRequestDto);
        return ApiResponseEntity.ok(location);
    }
}
