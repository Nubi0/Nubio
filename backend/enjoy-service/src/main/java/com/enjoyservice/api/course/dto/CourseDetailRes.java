package com.enjoyservice.api.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
public class CourseDetailRes {

    @JsonProperty("course_info")
    private CourseInfo courseInfo;
    @JsonProperty("place_list")
    private List<PlaceInfo> placeInfos;
    @JsonProperty("path_list")
    private List<PathInfo> pathInfos;

    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CourseInfo {
        @JsonProperty("course_id")
        private long courseId;
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
        @JsonProperty("time")
        private Integer time;
        @JsonProperty("type")
        private String type;
        @JsonProperty("distance")
        private Integer distance;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PlaceInfo {
        private long id;
        @JsonProperty("kakao_id")
        private long kakaoId;
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

    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PathInfo {
        @JsonProperty("longitude")
        private double longitude;

        @JsonProperty("latitude")
        private double latitude;

    }
}
