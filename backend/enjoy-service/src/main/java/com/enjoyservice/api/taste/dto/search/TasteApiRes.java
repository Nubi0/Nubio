package com.enjoyservice.api.taste.dto.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasteApiRes {

    @JsonProperty("food_type")
    private List<String> foodType;

    @JsonProperty("drink_type")
    private List<String> drinkType;

    @JsonProperty("play_type")
    private List<String> playType;

    public static TasteApiRes of(List<String> foodType,List<String> drinkType,List<String> playType) {
        return TasteApiRes.builder()
                .foodType(foodType)
                .drinkType(drinkType)
                .playType(playType).build();
    }
}
