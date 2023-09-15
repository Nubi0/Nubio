package com.enjoyservice.api.place.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class PlaceInfoRes {

    @JsonProperty("place_info")
    private PlaceInfo placeInfo;

    @JsonProperty("enjoy_meta")
    private Meta meta;

    @Setter
    @Getter
    @Builder
    static public class PlaceInfo {
        @JsonProperty("address_name")
        private String addressName;
        @JsonProperty("category_group_code")
        private String categoryGroupCode;
        @JsonProperty("category_group_name")
        private String categoryGroupName;
        @JsonProperty("category_name")
        private String categoryName;
        @JsonProperty("id")
        private String id;
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
        @JsonProperty("img_url")
        private String imgUrl;
    }

    @Setter
    @Getter
    @Builder
    static public class Meta {
        @JsonProperty("like_count")
        private int likeCount;
        @JsonProperty("like_flag")
        private boolean likeFlag;
        @JsonProperty("favorite_flag")
        private boolean favoriteFlag;
    }

}
