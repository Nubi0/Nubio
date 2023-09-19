package com.authenticationservice.api.member.service.impl;

import com.authenticationservice.api.member.dto.response.MemberResDto;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.domain.member.repository.MemberRepository;
import com.authenticationservice.global.jwt.dto.JwtDto;
import com.authenticationservice.global.jwt.service.JwtManager;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberInfoServiceImplTest {

    @Autowired
    private MemberInfoService memberInfoService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtManager jwtManager;
    @Autowired
    private EntityManager em;

    private Member savedBeforeMember;

    @BeforeEach
    void setUp() {
        Member beforeMember = Member.builder()
                .identification(Identification.createIdentification())
                .email(Email.from("beforeMember@nubio.com"))
                .nickname(Nickname.from("memberNickname"))
                .password(Password.of("pass", passwordEncoder))
                .oAuthType(OAuthType.NUBIO)
                .role(Role.ROLE_USERS)
                .gender(Gender.from("male"))
                .birth(Birth.from("2000-01-01"))
                .build();

        savedBeforeMember = memberRepository.save(beforeMember);
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
        em.flush();
        em.clear();
    }

    @DisplayName("회원 정보 조회를 성공한다.")
    @Test
    void getMemberInfo() {
        // given
        JwtDto jwtDto = jwtManager.createJwtDto(savedBeforeMember.getIdentification().getValue(), savedBeforeMember.getRole());
        MemberResDto res = memberInfoService.getMemberInfo(jwtDto.getAccessToken());
        // when then
        assertThat(res.getIdentification()).isEqualTo(savedBeforeMember.getIdentification());
        assertThat(res.getEmail()).isEqualTo(savedBeforeMember.getEmail());
        assertThat(res.getNickname()).isEqualTo(savedBeforeMember.getNickname());
        assertThat(res.getProfileUrl()).isEqualTo(savedBeforeMember.getProfileUrl());
    }

    @DisplayName("존재하는 email로 member를 조회하면 성공한다.")
    @Test
    void findByEmailSuccessful() {
        // given
        Email email = savedBeforeMember.getEmail();
        // when
        Member member = memberInfoService.findByEmail(email);
        // then
        assertThat(member).isEqualTo(savedBeforeMember);
    }

    @DisplayName("존재하는 identification으로 member를 조회하면 성공한다.")
    @Test
    void findByIdentificationSuccessful() {
        // given
        Identification identification = savedBeforeMember.getIdentification();
        // when
        Member member = memberInfoService.findByIdentification(identification);
        // then
        assertThat(member).isEqualTo(savedBeforeMember);
    }


    @DisplayName("회원 정보 조회를 성공한다.")
    @Test
    void getMemberByIdentificationSuccessful() {
        // given
        MemberResDto res = memberInfoService.getMemberByIdentification(savedBeforeMember.getIdentification().getValue());
        // when then
        assertThat(res.getIdentification()).isEqualTo(savedBeforeMember.getIdentification());
        assertThat(res.getEmail()).isEqualTo(savedBeforeMember.getEmail());
        assertThat(res.getNickname()).isEqualTo(savedBeforeMember.getNickname());
        assertThat(res.getProfileUrl()).isEqualTo(savedBeforeMember.getProfileUrl());
    }

    @DisplayName("회원 탈퇴를 성공한다.")
    @Test
    void withdrawSuccessful() {
        // given
        JwtDto jwtDto = jwtManager.createJwtDto(savedBeforeMember.getIdentification().getValue(), savedBeforeMember.getRole());
        // when
        memberInfoService.deleteMember(jwtDto.getAccessToken());
        // then
        Optional<Member> withdrewMember = memberRepository.findById(savedBeforeMember.getId());
        assertThat(withdrewMember).isPresent();

        Member member = withdrewMember.get();

        assertThat(member.getEmail().getValue()).contains(savedBeforeMember.getEmail().getValue());
        assertThat(member.getBirth().getValue()).isEqualTo(LocalDate.of(1000,1,1));
        assertThat(member.getPassword().getValue()).isEqualTo("탈퇴한 회원입니다.");
        if (member.getProfileUrl() != null) assertThat(member.getProfileUrl().getValue()).isEqualTo("탈퇴한 회원입니다.");
        assertThat(member.getActive().getValue()).isEqualTo(false);
    }
}