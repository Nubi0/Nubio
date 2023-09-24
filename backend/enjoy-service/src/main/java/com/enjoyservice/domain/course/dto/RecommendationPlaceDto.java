package com.enjoyservice.domain.course.dto;

import com.enjoyservice.domain.place.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationPlaceDto {

    private Long id;
    private int kakaoId;
    private String placeName;
    private String imgUrl;

    public static RecommendationPlaceDto of(Place place) {
        return RecommendationPlaceDto.builder()
                .id(place.getId())
                .kakaoId(place.getKakaoId().getValue())
                .placeName(place.getName().getValue())
                .imgUrl(!place.getImages().isEmpty()
                        ? place.getImages().get(0).getUrl().getValue() : "not exist")
                .build();
    }
}
