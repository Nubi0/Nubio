package com.authenticationservice.api.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NicknameCheckDto {

    private String nickname;

    @Builder
    public NicknameCheckDto(String nickname) {
        this.nickname = nickname;
    }
}
