package com.authenticationservice.external.email.controller;

import com.authenticationservice.api.ApiResponseEntity;
import com.authenticationservice.external.email.dto.request.EmailConfirmDto;
import com.authenticationservice.external.email.dto.request.EmailReqDto;
import com.authenticationservice.external.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
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
    @PostMapping()
    public ApiResponseEntity<String> certifyEmail(@Valid @RequestBody EmailReqDto emailDto) {
        return ApiResponseEntity.created(emailService.certifyEmail(emailDto));
    }

    // 인증번호 확인
    @Operation(summary = "인증번호 확인", description = "start/v1/email/confirms\n\n" )
    @PostMapping("/confirms")
    public ApiResponseEntity<String> SmsVerification(@Valid @RequestBody EmailConfirmDto emailConfirmDto) {
        emailService.verifyEmail(emailConfirmDto);
        return ApiResponseEntity.ok("Success");
    }

}
