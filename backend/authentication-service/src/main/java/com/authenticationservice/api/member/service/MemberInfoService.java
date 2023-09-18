package com.authenticationservice.api.member.service;

import com.authenticationservice.api.member.dto.response.MemberResDto;
import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.type.Email;
import com.authenticationservice.domain.member.entity.type.Identification;
import org.springframework.web.multipart.MultipartFile;


public interface MemberInfoService {
    MemberResDto getMemberInfo(String authorizedMember);
    MemberResDto getMemberByIdentification(String identification);
    Member findByEmail(Email email);
    Member findByIdentification(Identification identification);
    void updateMemberInfo(String authorizedMember, MultipartFile profileImg, String nickName);

}
