package com.authenticationservice.api.auth.service;

import com.authenticationservice.api.auth.dto.request.LoginReqDto;
import com.authenticationservice.api.auth.dto.request.SignupReqDto;
import com.authenticationservice.api.auth.dto.response.AccessTokenResDto;
import com.authenticationservice.api.auth.dto.response.SignResDto;
import com.authenticationservice.global.resolver.memberInfo.MemberInfoDto;

public interface AuthService {
    SignResDto signup(SignupReqDto signupReqDto);

    SignResDto login(LoginReqDto loginReqDto);

    void logout(MemberInfoDto memberInfo, String authorizationHeader);

    AccessTokenResDto createAccessTokenByRefreshToken(String refreshToken);
}
