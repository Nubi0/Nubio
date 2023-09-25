package com.enjoyservice.api.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CourseListRes {

    @JsonProperty("course_list")
    private List<CourseInfo> courseInfos;
    private Meta meta;

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Setter
    @Getter
    public static class CourseInfo {
        @JsonProperty("course_id")
        private long courseId;
        private String title;
        @JsonProperty("course_tags")
        private List<String> courseTags;
        @JsonProperty("favorite_flag")
        private boolean favoriteFlag;
        @JsonProperty("like_count")
        private long likeCount;
        @JsonProperty("like_flag")
        private boolean likeFlag;
        @JsonProperty("place_list")
        private List<PlaceInfo> placeInfos;

        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        @Setter
        @Getter
        public static class PlaceInfo {

            @JsonProperty("place_id")
            private long placeId;
            @JsonProperty("kakao_id")
            private int kakaoId;
            @JsonProperty("place_name")
            private String placeName;
            @JsonProperty("img_url")
            private String imageUrl;
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
    }
}
