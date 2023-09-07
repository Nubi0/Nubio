package com.authenticationservice.web.dto;

import com.authenticationservice.domain.member.entity.constant.Role;
import com.authenticationservice.global.jwt.dto.JwtDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import feign.Response;
import lombok.*;

import java.util.Date;

public class OauthLoginDto {

    @Getter
    @Setter
    public static class Req {
        private String oauthType;
    }

    @Getter @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {

        private String grantType;

        private String accessToken;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date accessTokenExpireTime;

        private String refreshToken;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date refreshTokenExpireTime;

        private Role role;

        public static Res of(JwtDto jwtTokenDto, Role role) {
            return Res.builder()
                    .grantType(jwtTokenDto.getGrantType())
                    .accessToken(jwtTokenDto.getAccessToken())
                    .accessTokenExpireTime(jwtTokenDto.getAccessTokenExpireTime())
                    .refreshToken(jwtTokenDto.getRefreshToken())
                    .refreshTokenExpireTime(jwtTokenDto.getRefreshTokenExpireTime())
                    .role(role)
                    .build();
        }

    }

}
