package com.authenticationservice.api.auth.service.impl;

import com.authenticationservice.api.auth.dto.request.LoginReqDto;
import com.authenticationservice.api.auth.dto.request.SignupReqDto;
import com.authenticationservice.api.auth.dto.response.SignResDto;
import com.authenticationservice.api.auth.exception.InvalidPasswordException;
import com.authenticationservice.api.auth.service.AuthService;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.domain.member.repository.MemberRepository;
import com.authenticationservice.global.jwt.service.JwtManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthServiceImplTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtManager jwtManager;

    private Member savedBeforeMember;

    @BeforeEach
    void setUp() {
        memberRepository.findByEmail(Email.from("beforeMember@nubio.com"))
                .ifPresent(member -> memberRepository.delete(member));

        Member beforeMember = Member.builder()
                .identification(Identification.createIdentification())
                .email(Email.from("beforeMember@nubio.com"))
                .nickname(Nickname.from("memberNickname"))
                .password(Password.of("pass", passwordEncoder))
                .oAuthType(OAuthType.NUBIO)
                .role(Role.ROLE_USER)
                .gender(Gender.from("male"))
                .birth(Birth.from("2000-01-01"))
                .build();

        savedBeforeMember = memberRepository.save(beforeMember);
    }


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


    @DisplayName("로그인에 성공한다.")
    @Test
    void loginSuccessful() {
        // given
        LoginReqDto request = LoginReqDto.builder()
                .email("beforeMember@nubio.com")
                .password("pass")
                .build();
        // when
        SignResDto result = authService.login(request);
        // then
        assertThat(jwtManager.getTokenClaims(result.getAccessToken()).get("identification")).isEqualTo(savedBeforeMember.getIdentification().getValue());
    }

    @DisplayName("로그아웃에 성공한다.")
    @Test
    void logoutSuccessful()  {
        // given
        LoginReqDto request = LoginReqDto.builder()
                .email("beforeMember@nubio.com")
                .password("pass")
                .build();
        SignResDto result = authService.login(request);

        MockHttpServletRequest testUser = new MockHttpServletRequest();
        testUser.addHeader("Authorization", "bearer " + result.getAccessToken());
        // then
        String authorizationHeader = testUser.getHeader("Authorization");

        authService.logout(authorizationHeader);
    }
}