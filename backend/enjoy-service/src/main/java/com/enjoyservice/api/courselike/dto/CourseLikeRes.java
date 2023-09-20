package com.enjoyservice.api.courselike.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseLikeRes {
    @JsonProperty("course_id")
    private long courseId;
    @JsonProperty("like_count")
    private long likeCount;
    @JsonProperty("like_flag")
    private boolean likeFlag;
}
