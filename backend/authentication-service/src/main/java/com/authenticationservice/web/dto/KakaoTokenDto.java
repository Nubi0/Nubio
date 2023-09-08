package com.authenticationservice.web.dto;

import lombok.*;

public class KakaoTokenDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private String grant_type;
        private String client_id;
        private String redirect_uri;
        private String code;
        private String client_secret;
    }

    @ToString
    @Builder @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Res {
        private String token_type;
        private String access_token;
        private Integer expires_in;
        private String refresh_token;
        private Integer refresh_token_expires_in;
        private String scope;
    }

}
