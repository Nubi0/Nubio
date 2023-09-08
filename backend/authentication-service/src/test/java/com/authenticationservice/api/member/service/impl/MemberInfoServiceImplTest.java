package com.authenticationservice.api.member.service.impl;

import com.authenticationservice.api.member.dto.request.SignupReqDto;
import com.authenticationservice.api.member.dto.response.SignupResDto;
import com.authenticationservice.api.member.exception.InvalidPasswordException;
import com.authenticationservice.api.member.service.MemberInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberInfoServiceImplTest {

    @Autowired
    private MemberInfoService memberInfoService;


    @DisplayName("Nubio 자체 회원가입에 성공한다.")
    @Test
    void signup_O() {
        // given
        SignupReqDto request = new SignupReqDto();
        request.setPassword("password");
        request.setPasswordCheck("password");
        request.setEmail("Member123@nubio.com");
        request.setNickname("nickname");
        request.setGender("male");
        request.setBirth("2000-01-01");

        // when
        SignupResDto result = memberInfoService.signup(request);

        // then
        assertNotNull(result);
    }


    @DisplayName("password와 passwordCheck가 일치하지 않아 실패한다.")
    @Test
    void passwordMismatch() {
        // given
        SignupReqDto request = new SignupReqDto();
        request.setPassword("password");
        request.setPasswordCheck("passwordCheck");
        request.setEmail("Member123@nubio.com");
        request.setNickname("nickname");
        request.setGender("male");
        request.setBirth("2000-01-01");

        // when then
        assertThrows(InvalidPasswordException.class, () -> {
            memberInfoService.signup(request);
        });
    }


}