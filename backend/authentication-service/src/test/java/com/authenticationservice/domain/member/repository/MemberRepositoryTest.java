package com.authenticationservice.domain.member.repository;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member beforeMember;

    @BeforeEach
    void setUp() {
        beforeMember = Member.builder()
                .identification(new Identification())
                .email(Email.from("beforeMember@nubio.com"))
                .nickname(Nickname.from("memberNickname"))
                .password(Password.of("pass"))
                .oAuthType(OAuthType.NUBIO)
                .role(Role.ROLE_USER)
                .gender(Gender.from("male"))
                .birth(Birth.from("2000-01-01"))
                .build();

        Member savedBeforeMember = memberRepository.save(beforeMember);
    }

    @DisplayName("존재하는 Email로 Member를 조회할 수 있다.")
    @Test
    void findByEmail() {
        // given
        Email email = beforeMember.getEmail();

        // when
        Optional<Member> targetMember = memberRepository.findByEmail(email);

        // then
        assertThat(targetMember.get()).isEqualTo(beforeMember);
    }

}