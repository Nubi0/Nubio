package com.enjoyservice.api.recommendation.dto.fastapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FastCreateReq {

    @JsonProperty("region")
    private String region;

    public static FastCreateReq from(String region) {
        return FastCreateReq.builder()
                .region(region).build();
    }
}
