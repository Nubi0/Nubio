package com.enjoyservice.api.recommendation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationReq {

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("latitude")
    private double latitude;
}
