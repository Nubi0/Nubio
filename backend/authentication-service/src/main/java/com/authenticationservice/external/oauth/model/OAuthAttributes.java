package com.authenticationservice.external.oauth.model;

import com.authenticationservice.domain.member.entity.Member;
import com.authenticationservice.domain.member.entity.constant.OAuthType;
import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.domain.member.entity.type.Email;
import com.authenticationservice.domain.member.entity.type.Nickname;
import com.authenticationservice.domain.member.entity.type.ProfileUrl;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class OAuthAttributes { // 회원 정보 가져올 때 통일시킴

    private String nickname;
    private String email;
    //private String profileUrl;
    private OAuthType oauthType;

    public Member toMemberEntity(OAuthType oauthType, Role role) {
        return Member.builder()
                .nickname(Nickname.from(nickname))
                .email(Email.from(email))
                .oAuthType(oauthType)
                //.profileUrl(ProfileUrl.from(profileUrl))
                .role(role)
                .build();
    }

}
