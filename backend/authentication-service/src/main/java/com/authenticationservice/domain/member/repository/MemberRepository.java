package com.authenticationservice.domain.member.repository;

import com.authenticationservice.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
