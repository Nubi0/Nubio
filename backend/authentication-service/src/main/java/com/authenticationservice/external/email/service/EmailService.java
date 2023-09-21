package com.authenticationservice.external.email.service;

import com.authenticationservice.external.email.dto.request.EmailReqDto;

public interface EmailService{
    String certifyEmail(EmailReqDto emailDto);
}
