package com.enjoyservice.api.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
public class CourseDetailRes {

    @JsonProperty("course_info")
    private CourseInfo courseInfo;
    @JsonProperty("place_list")
    private List<PlaceInfo> placeInfos;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CourseInfo {
        private String title;
        private String content;
        @JsonProperty("course_tags")
        private List<String> courseTags;
        @JsonProperty("favorite_flag")
        private boolean favoriteFlag;
        @JsonProperty("like_flag")
        private boolean likeFlag;
        @JsonProperty("like_count")
        private long likeCount;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PlaceInfo {
        private long id;
        @JsonProperty("address_name")
        private String addressName;
        @JsonProperty("category_group_code")
        private String categoryGroupCode;
        @JsonProperty("category_group_name")
        private String categoryGroupName;
        private String phone;
        @JsonProperty("place_name")
        private String placeName;
        @JsonProperty("place_url")
        private String placeUrl;
        @JsonProperty("road_address_name")
        private String roadAddressName;
        @JsonProperty("x")
        private String longitude;
        @JsonProperty("y")
        private String latitude;
        private int sequence;
    }
}
