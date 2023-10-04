package com.authenticationservice.domain.member.service.impl;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.domain.member.exception.DuplicateMemberException;
import com.authenticationservice.domain.member.exception.InvalidEmailFormatException;
import com.authenticationservice.domain.member.repository.MemberRepository;
import com.authenticationservice.domain.member.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Member savedBeforeMember;

    @BeforeEach
    void before() {
        Member beforeMember = Member.builder()
                .identification(Identification.createIdentification())
                .email(Email.from("beforeMember123@nubio.com"))
                .nickname(Nickname.from("beforeMemberNickname"))
                .password(Password.of("pass", passwordEncoder))
                .oAuthType(OAuthType.NUBIO)
                .role(Role.ROLE_USER)
                .gender(Gender.from("male"))
                .birth(Birth.from("2000-01-01"))
                .build();

        savedBeforeMember = memberRepository.save(beforeMember);
    }

    @AfterEach
    void after() {
        memberRepository.deleteAll();
    }

    @DisplayName("새로운 회원은 회원가입에 성공한다.")
    @Test
    void register() {
        // given
        Member member = Member.builder()
                .identification(Identification.createIdentification())
                .email(Email.from("member@nubio.com"))
                .nickname(Nickname.from("memberNickname"))
                .password(Password.of("pass", passwordEncoder))
                .oAuthType(OAuthType.NUBIO)
                .role(Role.ROLE_USER)
                .gender(Gender.from("male"))
                .birth(Birth.from("1996-05-08"))
                .build();
        // when
        Member registeredMember = memberService.register(member, null);
        // then
        assertThat(member).isEqualTo(registeredMember);
        assertThat(member.getEmail()).isEqualTo(registeredMember.getEmail());
        assertThat(member.getNickname()).isEqualTo(registeredMember.getNickname());
        assertThat(member.getPassword()).isEqualTo(registeredMember.getPassword());
        assertThat(member.getOAuthType()).isEqualTo(registeredMember.getOAuthType());
        assertThat(member.getRole()).isEqualTo(registeredMember.getRole());
        assertThat(member.getGender()).isEqualTo(registeredMember.getGender());
        assertThat(member.getBirth()).isEqualTo(registeredMember.getBirth());
    }

    @DisplayName("email이 중복이면 회원가입에 실패한다.")
    @Test
    void duplicateEmail() {
        // given
        String beforeMemberEmail = savedBeforeMember.getEmail().getValue();

        Member member = Member.builder()
                .identification(Identification.createIdentification())
                .email(Email.from(beforeMemberEmail))
                .nickname(Nickname.from("memberNickname"))
                .password(Password.of("pass", passwordEncoder))
                .oAuthType(OAuthType.NUBIO)
                .role(Role.ROLE_USER)
                .gender(Gender.from("male"))
                .birth(Birth.from("1996-05-08"))
                .build();
        // when then
        assertThatThrownBy(() -> memberService.register(member, null))
                .isInstanceOf(DuplicateMemberException.class);
    }

    @DisplayName("email에 @가 없으면 실패한다.")
    @Test
    void invalidEmailFormat() {
        // given
        String invalidFormatEmail = "invalid.naver.com";

        // when then
        assertThatThrownBy(() -> Email.from(invalidFormatEmail))
                .isInstanceOf(InvalidEmailFormatException.class);
    }

    @Test
    @DisplayName("존재하는 email로 member를 조회하면 성공한다.")
    void findByEmail1() {
        // given
        Email email = savedBeforeMember.getEmail();
        // when
        Optional<Member> opMember = memberService.findByEmail(email);
        // then
        assertThat(opMember.get()).isEqualTo(savedBeforeMember);
    }
    
}