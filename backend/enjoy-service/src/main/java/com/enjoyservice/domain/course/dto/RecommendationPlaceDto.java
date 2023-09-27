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
    private long kakaoId;
    private String placeName;
    private String imgUrl;

    public static RecommendationPlaceDto of(Place place) {
        return RecommendationPlaceDto.builder()
                .id(place.getId())
                .kakaoId(place.getKakaoId().getValue())
                .placeName(place.getName().getValue())
                .imgUrl(place.getImages().stream().map(image -> image.getUrl().getValue()).findFirst().isPresent()
                        ? place.getImages().stream().map(image -> image.getUrl().getValue()).findFirst().get() : "not exist")
                .build();
    }
}
