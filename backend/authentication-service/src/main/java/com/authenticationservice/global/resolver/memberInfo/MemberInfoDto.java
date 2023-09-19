package com.authenticationservice.global.resolver.memberInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto {

    private String identification;
    private String role;
}