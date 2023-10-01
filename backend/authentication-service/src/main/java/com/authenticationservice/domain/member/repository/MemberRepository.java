package com.authenticationservice.domain.member.repository;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.type.Email;
import com.authenticationservice.domain.member.entity.type.Identification;
import com.authenticationservice.domain.member.entity.type.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(Email email);

    Optional<Member> findByIdentification(Identification identification);

    Optional<Member> findByRefreshToken(String refreshToken);

    Optional<Member> findFirstByNickname(Nickname nickname);
}
