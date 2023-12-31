package com.authenticationservice.domain.member.service;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.type.Email;

import java.util.Optional;

public interface MemberService {

    Member register(Member member, String profileUrl);

    Optional<Member> findByEmail(Email email);

    Member findByRefreshToken(String refreshToken);
}
