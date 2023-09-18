package com.authenticationservice.api.member.controller;

import com.authenticationservice.api.ApiResponse;
import com.authenticationservice.api.member.dto.response.MemberResDto;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    final MemberInfoService memberInfoService;

    @GetMapping("/me")
    public ApiResponse<MemberResDto> info() {
        String authorizedMember = SecurityUtil.getAuthorizedMember();

        return ApiResponse.ok(memberInfoService.getMemberInfo(authorizedMember));
    }

    @GetMapping("/me/{identification}")
    public ApiResponse<MemberResDto> info(@PathVariable(name = "identification") String identification) {

        return ApiResponse.ok(memberInfoService.getMemberByIdentification(identification));
    }

    @PatchMapping("/me")
    public ApiResponse<String> updateMe(@RequestPart(name = "profileUrl", required = false) MultipartFile profileImg,
                                        @RequestPart(name = "nickname", required = false) String nickName) {
        String authorizedMember = SecurityUtil.getAuthorizedMember();
        memberInfoService.updateMemberInfo(authorizedMember, profileImg, nickName);
        return ApiResponse.ok("Success");
    }


}
