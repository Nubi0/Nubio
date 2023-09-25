package com.enjoyservice.api.coursereview.dto;

import jakarta.validation.constraints.*;
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

        @NotNull(message = "리뷰 점수는 필수값입니다.")
        @Max(value = 5, message = "리뷰 점수는 최대 5점입니다.")
        @Min(value = 0, message = "리뷰 점수는 최소 0점입니다.")
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
