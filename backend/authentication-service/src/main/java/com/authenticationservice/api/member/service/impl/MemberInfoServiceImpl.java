package com.authenticationservice.api.member.service.impl;

import com.authenticationservice.api.member.dto.response.MemberResDto;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.domain.member.exception.MemberNotFoundException;
import com.authenticationservice.domain.member.repository.MemberRepository;
import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.jwt.service.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Service("memberInfoService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberRepository memberRepository;
    private final JwtManager jwtManager;

    @Override
    public MemberResDto getMemberInfo(String authorizedMember) {
        Identification identification = Identification.from(jwtManager.getTokenClaims(authorizedMember).get("identification").toString());
        Member member = findByIdentification(identification);
        return new MemberResDto().of(member);
    }



    @Override
    public Member findByIdentification(Identification identification) {
        return memberRepository.findByIdentification(identification)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }

}
