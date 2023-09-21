package com.authenticationservice.api.auth.controller;

import com.authenticationservice.api.ApiResponseEntity;
import com.authenticationservice.api.auth.dto.request.LoginReqDto;
import com.authenticationservice.api.auth.dto.request.SignupReqDto;
import com.authenticationservice.api.auth.dto.response.SignResDto;
import com.authenticationservice.api.auth.service.AuthService;
import com.authenticationservice.global.resolver.memberInfo.MemberInfo;
import com.authenticationservice.global.resolver.memberInfo.MemberInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
@Tag(name = "3. User API", description = "자체 Auth api")
public class AuthController {

    final AuthService authService;

    @Operation(summary = "회원가입", description = "start/v1/member/signup\n\n" )
    @PostMapping("/signup")
    public ApiResponseEntity<SignResDto> signup(@Valid @RequestBody SignupReqDto signupReqDto) {
        return ApiResponseEntity.created(authService.signup(signupReqDto));
    }

    @Operation(summary = "로그인", description = "start/v1/member/login\n\n" )
    @PostMapping("/login")
    public ApiResponseEntity<SignResDto> login(@Valid @RequestBody LoginReqDto loginReqDto) {
        return ApiResponseEntity.ok(authService.login(loginReqDto));
    }

    @Operation(summary = "로그아웃", description = "start/v1/member/logout\n\n" )
    @PostMapping("/logout")
    public ApiResponseEntity<String> logout(@MemberInfo MemberInfoDto memberInfo, HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        authService.logout(memberInfo, authorizationHeader);
        return ApiResponseEntity.ok("Success");
    }

}
