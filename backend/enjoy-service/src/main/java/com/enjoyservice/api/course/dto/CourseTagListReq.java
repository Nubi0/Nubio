package com.enjoyservice.api.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CourseTagListReq {
    @JsonProperty("course_tags")
    private List<String> course_tags;
}
