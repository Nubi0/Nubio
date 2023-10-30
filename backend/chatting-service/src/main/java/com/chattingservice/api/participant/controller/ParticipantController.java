package com.chattingservice.api.participant.controller;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.api.participant.dto.request.FindByMemberReq;
import com.chattingservice.api.participant.dto.request.UpdateLastReadMsgReq;
import com.chattingservice.api.participant.dto.response.ParticipantRes;
import com.chattingservice.api.participant.service.ParticipantInfoService;
import com.chattingservice.global.resolver.memberInfo.MemberInfo;
import com.chattingservice.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Participant API", description = "채팅방 참석자  API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/participant")
public class ParticipantController {

    private final ParticipantInfoService participantInfoService;

    @Operation(summary = "프로필 수정", description = "chatting/v1/participant/profile\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PatchMapping("/profile")
    public ApiResponseEntity<ParticipantRes> updateMe(@MemberInfo MemberInfoDto memberInfoDto,
                                              @RequestPart(name = "room_id", required = true) Long roomId,
                                              @RequestPart(name = "profile_url", required = false) MultipartFile profileImg,
                                              @RequestPart(name = "nickname", required = false) String nickName
                                             ) {
        ParticipantRes participantRes = participantInfoService.updateMemberInfo(memberInfoDto.getMemberId(), roomId, profileImg, nickName);
        return ApiResponseEntity.ok(participantRes);
    }

    @Operation(summary = "마지막 읽은 메시지 수정", description = "chatting/v1/participant/update/last-read\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/update/last-read")
    public ApiResponseEntity<ParticipantRes> updateLastReadMsgId( @MemberInfo MemberInfoDto memberInfoDto,
            @RequestBody UpdateLastReadMsgReq updateLastReadMsgReq
    ) {
        ParticipantRes participantRes = participantInfoService.updateLastReadMsgId(memberInfoDto.getMemberId(), updateLastReadMsgReq.getRoomId(), updateLastReadMsgReq.getLastReadMsg());
        return ApiResponseEntity.ok(participantRes);
    }


    @Operation(summary = "memberId로 조회", description = "chatting/v1/participant/member-id\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PostMapping("/member-id")
    public ApiResponseEntity<ParticipantRes> findByMemberId(@RequestBody FindByMemberReq findByMemberReq

    ) {
        ParticipantRes participantRes = participantInfoService.findByMemberId(findByMemberReq.getMemberId(), findByMemberReq.getRoomId());
        return ApiResponseEntity.ok(participantRes);
    }

}
