package com.authenticationservice.domain.member.service.impl;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.type.Email;
import com.authenticationservice.domain.member.entity.type.Identification;
import com.authenticationservice.domain.member.exception.DuplicateMemberException;
import com.authenticationservice.domain.member.repository.MemberRepository;
import com.authenticationservice.domain.member.service.MemberService;
import com.authenticationservice.domain.profile.entity.Profile;
import com.authenticationservice.domain.profile.entity.type.Active;
import com.authenticationservice.domain.profile.entity.type.FileName;
import com.authenticationservice.domain.profile.entity.type.FileSize;
import com.authenticationservice.domain.profile.entity.type.FileUrl;
import com.authenticationservice.domain.profile.repository.ProfileRepository;
import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public Member register(Member member, String profileUrl) {
        validateDuplicateMember(member);
        member.setIdentification(Identification.createIdentification());
        Profile profile = Profile.builder()
                .member(member)
                .fileUrl(FileUrl.from(profileUrl))
                .fileSize(FileSize.from(1L))
                .active(Active.from(true))
                .build();
        member.setProfile(profile);
//        Profile savedProfile = profileRepository.save(profile);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member)  {
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        if(optionalMember.isPresent()) {
            throw new DuplicateMemberException(ErrorCode.DUPLICATE_MEMBER_EXIST);
        }
    }

    @Override
    public Optional<Member> findByEmail(Email email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Member findByRefreshToken(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        LocalDateTime tokenExpirationTime = member.getRefreshTokenExpirationTime();
        if(tokenExpirationTime.isBefore(LocalDateTime.now())) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
        return member;
    }
}
