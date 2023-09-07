package com.authenticationservice.domain.member.service.impl;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.exception.DuplicateMemberException;
import com.authenticationservice.domain.member.repository.MemberRepository;
import com.authenticationservice.domain.member.service.MemberService;
import com.authenticationservice.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member register(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member)  {
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        if(optionalMember.isPresent()) {
            throw new DuplicateMemberException(ErrorCode.DUPLICATE_MEMBER_EXIST);
        }
    }
}
