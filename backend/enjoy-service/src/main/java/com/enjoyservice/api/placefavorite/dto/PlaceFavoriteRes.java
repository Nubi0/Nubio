package com.enjoyservice.api.placefavorite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceFavoriteRes {

    @JsonProperty("place_id")
    private long placeId;
    @JsonProperty("place_favorite_flag")
    private boolean placeFavoriteFlag;
}
