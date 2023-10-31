package com.chattingservice.global.resolver.memberInfo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoDto {

    private String memberId;
    private String role;
}
