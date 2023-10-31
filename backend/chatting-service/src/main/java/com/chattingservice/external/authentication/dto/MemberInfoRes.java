package com.chattingservice.external.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoRes {

    private String code;
    private String status;
    @JsonProperty("data")
    private Data data;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Data {
        private String identification;
        @JsonProperty("nickname")
        private String nickname;
        private String email;
        @JsonProperty("profileUrl")
        private String profileUrl;
    }
}
