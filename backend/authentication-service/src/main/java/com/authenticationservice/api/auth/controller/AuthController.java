package com.authenticationservice.api.auth.controller;

import com.authenticationservice.api.ApiResponse;
import com.authenticationservice.api.auth.dto.request.LoginReqDto;
import com.authenticationservice.api.auth.dto.request.SignupReqDto;
import com.authenticationservice.api.auth.dto.response.SignResDto;
import com.authenticationservice.api.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
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
public class AuthController {

    final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<SignResDto> signup(@RequestBody SignupReqDto signupReqDto) {
        return ApiResponse.created(authService.signup(signupReqDto));
    }

    @PostMapping("/login")
    public ApiResponse<SignResDto> login(@RequestBody LoginReqDto loginReqDto) {
        return ApiResponse.ok(authService.login(loginReqDto));
    }



}
