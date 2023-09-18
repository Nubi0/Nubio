package com.authenticationservice.api.auth.service.impl;


import com.authenticationservice.api.auth.dto.request.SignupReqDto;
import com.authenticationservice.api.auth.dto.response.SignResDto;
import com.authenticationservice.api.auth.exception.InvalidPasswordException;
import com.authenticationservice.api.auth.service.AuthService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceImplTest {

    @Autowired
    private AuthService authService;



    @DisplayName("Nubio 자체 회원가입에 성공한다.")
    @Test
    void signupSuccessful() {
        // given
        SignupReqDto request = SignupReqDto.builder()
                .email("Member123@nubio.com")
                .password("password")
                .passwordCheck("password")
                .nickname("nickname")
                .gender("male")
                .birth("2000-01-01")
                .build();
        // when
        SignResDto result = authService.signup(request);
        // then
        assertNotNull(result);
    }


    @DisplayName("password와 passwordCheck가 일치하지 않아 실패한다.")
    @Test
    void passwordMismatch() {
        // given
        SignupReqDto request = SignupReqDto.builder()
                .email("Member123@nubio.com")
                .password("password")
                .passwordCheck("passwordCheck")
                .nickname("nickname")
                .gender("male")
                .birth("2000-01-01")
                .build();
        // when then
        assertThrows(InvalidPasswordException.class, () -> {
            authService.signup(request);
        });
    }



}