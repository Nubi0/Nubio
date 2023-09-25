package com.enjoyservice.api.recommendation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRes {

    @JsonProperty("course_list")
    private List<CourseInfoDto> courseList;

    public static RecommendationRes from(List<CourseInfoDto> courseList) {
        return RecommendationRes.builder()
                .courseList(courseList).build();
    }
}
