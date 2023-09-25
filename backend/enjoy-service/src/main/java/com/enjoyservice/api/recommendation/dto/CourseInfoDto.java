package com.enjoyservice.api.recommendation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseInfoDto {

    @JsonProperty("course_id")
    private Long id;

    @JsonProperty("title")
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
    private List<PlaceInfoDto> placeInfos;

    public static CourseInfoDto of(Long id, String title, List<String> courseTags, boolean favoriteFlag,
                                   long likeCount, boolean likeFlag, List<PlaceInfoDto> placeInfos) {
        return CourseInfoDto.builder()
                .id(id)
                .title(title)
                .courseTags(courseTags)
                .favoriteFlag(favoriteFlag)
                .likeCount(likeCount)
                .likeFlag(likeFlag)
                .placeInfos(placeInfos).build();
    }
}
