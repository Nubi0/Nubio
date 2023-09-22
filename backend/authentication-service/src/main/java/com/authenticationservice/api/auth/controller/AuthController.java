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
            @ApiResponse(responseCode = "M-001", description =  "INVALID_EMAIL_FORMAT", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-001\", \"errorMessage\": \"이메일 형식이 잘못됐습니다.\"}"))),
            @ApiResponse(responseCode = "M-002", description =  "INVALID_OAUTHTYPE", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-002\", \"errorMessage\": \"로그인 타입이 잘못됐습니다.\"}"))),
            @ApiResponse(responseCode = "M-003", description =  "INVALID_ROLE", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-003\", \"errorMessage\": \"회원 역할 타입이 잘못됐습니다.\"}"))),
            @ApiResponse(responseCode = "M-004", description =  "INVALID_GENDER", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-004\", \"errorMessage\": \"성별 타입이 잘못됐습니다.\"}"))),
            @ApiResponse(responseCode = "M-005", description =  "INVALID_BIRTH_FORMAT", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-005\", \"errorMessage\": \"생년월일 형식이 잘못됐습니다.\"}"))),
            @ApiResponse(responseCode = "M-006", description =  "DUPLICATE_MEMBER_EXIST", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-006\", \"errorMessage\": \"중복된 회원가입 입니다.\"}"))),
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
            @ApiResponse(responseCode = "M-001", description =  "INVALID_EMAIL_FORMAT", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-001\", \"errorMessage\": \"이메일 형식이 잘못됐습니다.\"}"))),
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
            @ApiResponse(responseCode = "A-001", description =  "TOKEN_EXPIRED", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-001\", \"errorMessage\": \"토큰이 만료되었습니다.\"}"))),
            @ApiResponse(responseCode = "A-002", description =  "NOT_VALID_TOKEN", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-002\", \"errorMessage\": \"해당 토큰은 유효한 토큰이 아닙니다.\"}"))),
            @ApiResponse(responseCode = "M-007", description =  "MEMBER_NOT_EXISTS", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-007\", \"errorMessage\": \"해당 회원은 존재하지 않습니다.\"}")))
    })
    @PostMapping("/logout")
    public ApiResponseEntity<String> logout(@MemberInfo MemberInfoDto memberInfo, HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        authService.logout(memberInfo, authorizationHeader);
        return ApiResponseEntity.ok("Success");
    }

}
