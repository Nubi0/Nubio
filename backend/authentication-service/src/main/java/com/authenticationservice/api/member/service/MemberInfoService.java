package com.authenticationservice.api.member.service;

import com.authenticationservice.api.member.dto.request.SignupReqDto;
import com.authenticationservice.api.member.dto.response.SignupResDto;

public interface MemberInfoService {
    SignupResDto signup(SignupReqDto signupReqDto);
}
