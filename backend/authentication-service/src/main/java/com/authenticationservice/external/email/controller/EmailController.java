package com.authenticationservice.external.email.controller;

import com.authenticationservice.api.ApiResponse;
import com.authenticationservice.external.email.dto.request.EmailReqDto;
import com.authenticationservice.external.email.service.EmailService;
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

    @PostMapping()
    public ApiResponse<String> certifyEmail(@Valid @RequestBody EmailReqDto emailDto) {
        return ApiResponse.created(emailService.certifyEmail(emailDto));
    }

}
