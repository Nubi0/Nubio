package com.enjoyservice.api.coursefavorite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseFavoriteRes {

    @JsonProperty("course_id")
    private long courseId;
    @JsonProperty("course_favorite_flag")
    private boolean courseFavoriteFlag;
}
