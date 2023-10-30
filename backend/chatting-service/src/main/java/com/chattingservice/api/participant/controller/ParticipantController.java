package com.chattingservice.api.participant.controller;

import com.chattingservice.api.ApiResponseEntity;
import com.chattingservice.api.participant.dto.response.ParticipantRes;
import com.chattingservice.api.participant.service.ParticipantInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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
    public ApiResponseEntity<ParticipantRes> updateMe(@RequestPart(name = "member_id", required = true) String memberId,
                                              @RequestPart(name = "room_id", required = true) Long roomId,
                                              @RequestPart(name = "profile_url", required = false) MultipartFile profileImg,
                                              @RequestPart(name = "nickname", required = false) String nickName
                                             ) {
        ParticipantRes participantRes = participantInfoService.updateMemberInfo(memberId, roomId, profileImg, nickName);
        return ApiResponseEntity.ok(participantRes);
    }



}
