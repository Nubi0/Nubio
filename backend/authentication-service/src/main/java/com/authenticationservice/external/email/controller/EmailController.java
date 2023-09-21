package com.authenticationservice.external.email.controller;

import com.authenticationservice.api.ApiResponseEntity;
import com.authenticationservice.external.email.dto.request.EmailConfirmDto;
import com.authenticationservice.external.email.dto.request.EmailReqDto;
import com.authenticationservice.external.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/email")
@RequiredArgsConstructor
@Tag(name = "2. Email API", description = "Email 인증 api")
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "이메일 발송", description = "start/v1/email\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
            @ApiResponse(responseCode = "M-001", description =  "INVALID_EMAIL_FORMAT", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-001\", \"errorMessage\": \"이메일 형식이 잘못됐습니다.\"}"))),
            @ApiResponse(responseCode = "M-009", description =  "EMAIL_IS_EXISTS", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-009\", \"errorMessage\": \"존재하는 이메일입니다.\"}")))
    })
    @PostMapping()
    public ApiResponseEntity<String> certifyEmail(@Valid @RequestBody EmailReqDto emailDto) {
        return ApiResponseEntity.created(emailService.certifyEmail(emailDto));
    }

    // 인증번호 확인
    @Operation(summary = "인증번호 확인", description = "start/v1/email/confirms\n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED"),
            @ApiResponse(responseCode = "A-003", description =  "EMAIL_CONFIRM_FAILED", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"A-003\", \"errorMessage\": \"이메일 인증 실패하였습니다.\"}"))),
            @ApiResponse(responseCode = "M-001", description =  "INVALID_EMAIL_FORMAT", content = @Content(examples = @ExampleObject(value = "{\"errorCode\": \"M-001\", \"errorMessage\": \"이메일 형식이 잘못됐습니다.\"}"))),
    })
    @PostMapping("/confirms")
    public ApiResponseEntity<String> SmsVerification(@Valid @RequestBody EmailConfirmDto emailConfirmDto) {
        emailService.verifyEmail(emailConfirmDto);
        return ApiResponseEntity.ok("Success");
    }

}
