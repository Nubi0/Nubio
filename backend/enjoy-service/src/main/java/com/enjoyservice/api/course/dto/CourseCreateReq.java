package com.enjoyservice.api.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseCreateReq {

    @NotBlank(message = "코스 제목은 필수값입니다.")
    @Size(max = 100, message = "코스 제목의 길이는 최대 100글자 입니다.")
    private String title;

    @NotBlank(message = "코스 설명은 필수값입니다.")
    private String content;

    @NotBlank(message = "코스 지역은 필수값입니다.")
    @Size(max = 100, message = "코스 지역의 길이는 최대 100글자 입니다.")
    private String region;

    @NotBlank(message = "공개 여부는 필수값입니다.")
    @JsonProperty("public_flag")
    private boolean publicFlag;

    @Valid
    @Size(max = 10, message = "코스 태그는 10개 까지만 지정할 수 있습니다.")
    @JsonProperty("course_tags")
    private List<TagInfo> courseTags;

    @Valid
    @NotNull(message = "코스 내 장소는 필수값입니다.")
    @Size(min = 1, max = 5, message = "장소는 최소 1개, 최대 5개가 포함되어야 합니다.")
    @JsonProperty("place_list")
    private List<PlaceInfo> placeInfos;

    @Getter
    static public class TagInfo {

        @NotBlank(message = "태그는 비어있으면 안됩니다.")
        @Size(max = 10, message = "태그의 길이는 최대 10글자 입니다.")
        private String value;
    }

    @Getter
    static public class PlaceInfo {

        @NotBlank(message = "장소 id는 필수값입니다.")
        @JsonProperty("place_id")
        private long placeId;

        @NotBlank(message = "장소 순서는 필수값입니다.")
        @Size(min = 1, max = 5, message = "장소 순서는 1 ~ 5 입니다.")
        private int sequence;
    }
}