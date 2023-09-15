package com.enjoyservice.api.placelike.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class PlaceLikeRes {

    @JsonProperty("like_count")
    private long likeCount;
    @JsonProperty("like_flag")
    private boolean likeFlag;
}
