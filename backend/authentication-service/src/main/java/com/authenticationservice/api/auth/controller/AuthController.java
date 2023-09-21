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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
            @ApiResponse(responseCode = "M-008", description =  "INVALID_PASSWORD_CHECK", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-008\", \"errorMessage\": \"비밀번호가 일치하지 않습니다.\"}"))),
            @ApiResponse(responseCode = "M-009", description =  "EMAIL_IS_EXISTS", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-009\", \"errorMessage\": \"존재하는 이메일입니다.\"}")))
    })
    @PostMapping("/signup")
    public ApiResponseEntity<SignResDto> signup(@Valid @RequestBody SignupReqDto signupReqDto) {
        return ApiResponseEntity.created(authService.signup(signupReqDto));
    }

    @Operation(summary = "로그인", description = "start/v1/member/login\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
            @ApiResponse(responseCode = "M-007", description =  "MEMBER_NOT_EXISTS", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-007\", \"errorMessage\": \"해당 회원은 존재하지 않습니다.\"}"))),
            @ApiResponse(responseCode = "M-008", description =  "INVALID_PASSWORD_CHECK", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-008\", \"errorMessage\": \"비밀번호가 일치하지 않습니다.\"}"))),
    })
    @PostMapping("/login")
    public ApiResponseEntity<SignResDto> login(@Valid @RequestBody LoginReqDto loginReqDto) {
        return ApiResponseEntity.ok(authService.login(loginReqDto));
    }

    @Operation(summary = "로그아웃", description = "start/v1/member/logout\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
            @ApiResponse(responseCode = "M-007", description =  "MEMBER_NOT_EXISTS", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-007\", \"errorMessage\": \"해당 회원은 존재하지 않습니다.\"}")))
    })
    @PostMapping("/logout")
    public ApiResponseEntity<String> logout(@MemberInfo MemberInfoDto memberInfo, HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        authService.logout(memberInfo, authorizationHeader);
        return ApiResponseEntity.ok("Success");
    }

}
