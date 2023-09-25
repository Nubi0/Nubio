package com.enjoyservice.api.coursereview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseReviewInfosRes {

    @JsonProperty("review_list")
    private List<CourseReviewInfo> courseReviewInfos;
    @JsonProperty("meta")
    private CourseReviewInfosRes.Meta meta;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CourseReviewInfo {
        @JsonProperty("course_reivew_id")
        private long courseReviewId;
        @JsonProperty("member_id")
        private String memberId;
        @JsonProperty("nickname")
        private String nickName;
        private String content;
        private int point;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class Meta {
        @JsonProperty("page_number")
        private long pageNumber;
        @JsonProperty("page_size")
        private long pageSize;
        @JsonProperty("total_pages")
        private long totalPages;
        @JsonProperty("total_elements")
        private long totalElements;
        @JsonProperty("last_flag")
        private boolean lastFlag;
    }


}
