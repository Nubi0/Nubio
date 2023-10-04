package com.authenticationservice.api.member.dto.response;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.type.Birth;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberResDto {
    private String identification;
    private String nickname;
    private String email;
    private String profileUrl;
    private String birth;
    private String gender;


    public MemberResDto of(Member member) {
        MemberResDto res = new MemberResDto();
        res.identification = member.getIdentification().getValue();
        res.nickname = member.getNickname().getValue();
        res.email = member.getEmail().getValue();
        res.profileUrl = member.getProfile().getFileUrl().getValue() ;
        res.birth = (member.getBirth() != null) ? member.getBirth().getValue().toString() : null;
        res.gender = (member.getGender() != null) ? member.getGender().getDescription() : null;

        return res;
    }
}
