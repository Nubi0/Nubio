package com.authenticationservice.api.member.dto.response;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.type.Email;
import com.authenticationservice.domain.member.entity.type.Identification;
import com.authenticationservice.domain.member.entity.type.Nickname;
import com.authenticationservice.domain.member.entity.type.ProfileUrl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResDto {
    private Identification identification;
    private Nickname nickname;
    private Email email;
    private ProfileUrl profileUrl;

    public MemberResDto of(Member member) {
        MemberResDto res = new MemberResDto();
        res.identification = member.getIdentification();
        res.nickname = member.getNickname();
        res.email = member.getEmail();
        res.profileUrl = member.getProfileUrl();
        return res;
    }
}
