package com.authenticationservice.api.member.service;

import com.authenticationservice.api.member.dto.response.MemberResDto;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.type.Email;
import com.authenticationservice.domain.member.entity.type.Identification;
import com.authenticationservice.global.resolver.memberInfo.MemberInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


public interface MemberInfoService {
    MemberResDto getMemberInfo(MemberInfoDto memberInfo);
    MemberResDto getMemberByIdentification(String identification);
    Optional<Member> findByEmail(Email email);
    Member findByIdentification(Identification identification);
    void updateMemberInfo(MemberInfoDto memberInfo, MultipartFile profileImg, String nickName, String gender, String birth);
    void deleteMember(MemberInfoDto memberInfo);
}
