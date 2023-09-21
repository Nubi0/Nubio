package com.authenticationservice.api.member.service.impl;

import com.authenticationservice.api.member.dto.response.MemberResDto;
import com.authenticationservice.api.member.service.MemberInfoService;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.Gender;
import com.authenticationservice.domain.member.entity.type.*;
import com.authenticationservice.domain.member.exception.MemberNotFoundException;
import com.authenticationservice.domain.member.repository.MemberRepository;
import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;



@Slf4j
@Service("memberInfoService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResDto getMemberInfo(MemberInfoDto memberInfo) {
        log.info("memberInfo = {}",memberInfo.getIdentification());
        Identification identification = Identification.from(memberInfo.getIdentification());
        Member member = findByIdentification(identification);
        return new MemberResDto().of(member);
    }

    @Override
    public MemberResDto getMemberByIdentification(String identification) {
        Member member = findByIdentification(Identification.from(identification));
        return new MemberResDto().of(member);
    }

    @Override
    public Optional<Member> findByEmail(Email email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Member findByIdentification(Identification identification) {
        return memberRepository.findByIdentification(identification)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }

    @Override
    @Transactional
    public void updateMemberInfo(MemberInfoDto memberInfo, MultipartFile profileImg, String nickname, String gender, String birth) {
        Identification identification = Identification.from(memberInfo.getIdentification());
        Member member = findByIdentification(identification);

        if(!profileImg.isEmpty()) {
            // TODO : 이미지 s3 저장
//            String url = fileService.uploadFile(profileImg, null).getUrl();
//            member.setProfileUrl(url);
        }
        if(!nickname.isEmpty()) member.setNickname(Nickname.from(nickname));
        if(!gender.isEmpty()) member.setGender(Gender.from(gender));
        if(!birth.isEmpty()) member.setBirth(Birth.from(birth));
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void deleteMember(MemberInfoDto memberInfo) {
        Identification identification = Identification.from(memberInfo.getIdentification());
        Member member = findByIdentification(identification);

        member.withdraw();
        memberRepository.save(member);
    }
}
