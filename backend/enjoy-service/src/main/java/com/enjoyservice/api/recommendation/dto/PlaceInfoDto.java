package com.enjoyservice.api.recommendation.dto;

import com.enjoyservice.domain.course.dto.RecommendationPlaceDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceInfoDto {

    @JsonProperty("place_id")
    private long placeId;

    @JsonProperty("kakao_id")
    private long kakaoId;

    @JsonProperty("place_name")
    private String placeName;

    @JsonProperty("img_url")
    private String imageUrl;

    public static PlaceInfoDto from (RecommendationPlaceDto recommendationPlaceDto) {
        return PlaceInfoDto.builder()
                .placeId(recommendationPlaceDto.getId())
                .kakaoId(recommendationPlaceDto.getKakaoId())
                .placeName(recommendationPlaceDto.getPlaceName())
                .imageUrl(recommendationPlaceDto.getImgUrl()).build();
    }
}
