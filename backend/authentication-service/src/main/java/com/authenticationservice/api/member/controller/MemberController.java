package com.authenticationservice.api.member.controller;

import com.authenticationservice.api.ApiResponse;
import com.authenticationservice.api.member.dto.response.MemberResDto;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.global.resolver.memberInfo.MemberInfo;
import com.authenticationservice.global.resolver.memberInfo.MemberInfoDto;
import com.authenticationservice.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Enumeration;

@Slf4j
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
@Tag(name = "3. Member API", description = "회원 api")
public class MemberController {

    final MemberInfoService memberInfoService;

    @Operation(summary = "프로필 조회", description = "auth/v1/member/me\n\n" )
    @GetMapping("/me")
    public ApiResponse<MemberResDto> info(@MemberInfo MemberInfoDto memberInfo, HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info("{} : {}", headerName ,headerValue);
        }
        return ApiResponse.ok(memberInfoService.getMemberInfo(memberInfo));
    }

    @Operation(summary = "identification으로 조회", description = "start/v1/member/me/{identification}\n\n" )
    @GetMapping("/me/{identification}")
    public ApiResponse<MemberResDto> info(@PathVariable(name = "identification") String identification) {

        return ApiResponse.ok(memberInfoService.getMemberByIdentification(identification));
    }

    @Operation(summary = "프로필 수정", description = "auth/v1/member/me\n\n" )
    @PatchMapping("/me")
    public ApiResponse<String> updateMe(@RequestPart(name = "profileUrl", required = false) MultipartFile profileImg,
                                        @RequestPart(name = "nickname", required = false) String nickName) {
        String authorizedMember = SecurityUtil.getAuthorizedMember();
        memberInfoService.updateMemberInfo(authorizedMember, profileImg, nickName);
        return ApiResponse.ok("Success");
    }

    @Operation(summary = "회원 탈퇴", description = "auth/v1/member/delete\n\n" )
    @PatchMapping("/delete")
    public ApiResponse<String> deleteMe() {
        String authorizedMember = SecurityUtil.getAuthorizedMember();
        memberInfoService.deleteMember(authorizedMember);
        return ApiResponse.ok("Success");
    }
}
