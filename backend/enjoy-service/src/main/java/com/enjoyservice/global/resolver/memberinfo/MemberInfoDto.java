package com.enjoyservice.global.resolver.memberinfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto {

    private String memberId;
    private String role;
}
