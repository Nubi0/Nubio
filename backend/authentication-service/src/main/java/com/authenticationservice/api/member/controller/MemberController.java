package com.authenticationservice.api.member.controller;

import com.authenticationservice.api.ApiResponse;
import com.authenticationservice.api.member.dto.request.SignupReqDto;
import com.authenticationservice.api.member.dto.response.SignupResDto;
import com.authenticationservice.api.member.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    final MemberInfoService memberInfoService;

    @PostMapping("/signup")
    public ApiResponse<SignupResDto> signup(@RequestBody SignupReqDto signupReqDto) {
         return ApiResponse.created(memberInfoService.signup(signupReqDto));
    }

}
