package com.authenticationservice.api.auth.service;

import com.authenticationservice.api.auth.dto.request.LoginReqDto;
import com.authenticationservice.api.auth.dto.request.SignupReqDto;
import com.authenticationservice.api.auth.dto.response.SignResDto;

public interface AuthService {
    SignResDto signup(SignupReqDto signupReqDto);

    SignResDto login(LoginReqDto loginReqDto);

    void logout(String authorizedMember);
}
