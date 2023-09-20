package com.authenticationservice.global.resolver.memberInfo;

import com.authenticationservice.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto {

    private String identification;
    private String role;

    @Builder
    public MemberInfoDto(String identification, String role) {
        this.identification = identification;
        this.role = role;
    }
}