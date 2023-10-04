package com.enjoyservice.api.profile.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCourseRes {

    @JsonProperty("course_list")
    private List<CourseInfo> courseInfos;
    private Meta meta;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CourseInfo {

        @JsonProperty("course_id")
        private long courseId;
        private String title;
        @JsonProperty("course_tags")
        private List<String> tagInfos;
        @JsonProperty("place_list")
        private List<PlaceInfo> placeInfos;


        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class PlaceInfo {
            @JsonProperty("place_id")
            private long placeId;
            @JsonProperty("kakao_id")
            private long kakaoId;
            @JsonProperty("place_name")
            private String placeName;
            @JsonProperty("img_url")
            private String imgUrl;
        }

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
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
    }
}
