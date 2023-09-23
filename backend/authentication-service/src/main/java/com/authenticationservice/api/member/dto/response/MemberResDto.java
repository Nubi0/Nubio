package com.authenticationservice.api.member.dto.response;

import com.authenticationservice.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResDto {
    private String identification;
    private String nickname;
    private String email;
    private String profileUrl;

    public MemberResDto of(Member member) {
        MemberResDto res = new MemberResDto();
        res.identification = member.getIdentification().getValue();
        res.nickname = member.getNickname().getValue();
        res.email = member.getEmail().getValue();
        res.profileUrl = (member.getProfileUrl() != null) ? member.getProfileUrl().getValue() : null;
        return res;
    }
}
