package com.enjoyservice.api.place.dto;

import com.enjoyservice.domain.place.entity.Place;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.dynalink.NamedOperation;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearPlaceInfoPageRes {

    @JsonProperty("content")
    List<PlaceInfo> content;

    @JsonProperty("meta")
    private Meta meta;

    static public NearPlaceInfoPageRes from(Page<Place> placePage) {
        return NearPlaceInfoPageRes.builder()
                .meta(Meta.from(placePage))
                .content(placePage.getContent().stream()
                        .map(place -> PlaceInfo.from(place))
                        .collect(Collectors.toList())
                ).build();
    }
    @Setter
    @Getter
    @Builder
    static public class PlaceInfo {

            @JsonProperty("place_id")
            private long placeId;
            @JsonProperty("kakao_id")
            private long kakaoId;
            @JsonProperty("address_name")
            private String addressName;
            @JsonProperty("category_group_code")
            private String categoryGroupCode;
            @JsonProperty("category_group_name")
            private String categoryGroupName;
            @JsonProperty("category_detail")
            private String categoryDetail;
            @JsonProperty("phone")
            private String phone;
            @JsonProperty("place_name")
            private String placeName;
            @JsonProperty("place_url")
            private String placeUrl;
            @JsonProperty("road_address_name")
            private String roadAddressName;
            @JsonProperty("longitude")
            private double longitude;
            @JsonProperty("latitude")
            private double latitude;


        static public PlaceInfo from(Place place) {
            return PlaceInfo.builder()
                    .placeId(place.getId())
                    .kakaoId(place.getKakaoId().getValue())
                    .addressName(place.getAddress().getName())
                    .categoryGroupCode(place.getCategory().getGroupCode().name())
                    .categoryGroupName(place.getCategory().getGroupName().name())
                    .categoryDetail(place.getCategory().getDetail().getValue())
                    .phone(place.getPhone().getValue())
                    .placeName(place.getName().getValue())
                    .placeUrl(place.getUrl().getValue())
                    .roadAddressName(place.getAddress().getRoadName().getValue())
                    .longitude(place.getPosition().getLongitude().getValue())
                    .latitude(place.getPosition().getLatitude().getValue())
                    .build();
        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Setter
    @Getter
    public static class Meta {
        @JsonProperty("page_number")
        private long pageNumber;
        @JsonProperty("page_size")
        private int pageSize;
        @JsonProperty("total_pages")
        private long totalPages;
        @JsonProperty("total_elements")
        private long totalElements;
        @JsonProperty("last_flag")
        private boolean lastFlag;

        static public Meta from(Page<Place> placePage){
            return  Meta.builder()
                    .pageNumber(placePage.getNumber())
                    .pageSize(placePage.getSize())
                    .totalElements(placePage.getTotalElements())
                    .totalPages(placePage.getTotalPages())
                    .lastFlag(placePage.isLast())
                    .build();
        }
    }


}
