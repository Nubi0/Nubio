package com.enjoyservice.api.recommendation.dto.fastapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FastRecoReq {

    @JsonProperty("region")
    private String region;

    @JsonProperty("words")
    private List<String> words;

    public static FastRecoReq of(String region, List<String> words) {
        return FastRecoReq.builder()
                .region(region)
                .words(words).build();
    }
}
