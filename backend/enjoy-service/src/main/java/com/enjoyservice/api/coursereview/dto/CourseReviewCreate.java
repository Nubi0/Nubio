package com.enjoyservice.api.coursereview.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CourseReviewCreate {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Req {
        @NotBlank(message = "리뷰 내용은 필수값입니다.")
        private String content;

        @NotBlank(message = "리뷰 점수는 필수값입니다.")
        @Size(min = 0, max = 5, message = "리뷰 점수는 0 ~ 5 입니다.")
        private int point;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {
        private long courseReviewId;
    }

}
