package com.enjoyservice.api.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCreateReq {

    @NotBlank(message = "코스 제목은 필수값입니다.")
    @Size(max = 100, message = "코스 제목의 길이는 최대 100글자 입니다.")
    private String title;

    @NotBlank(message = "코스 설명은 필수값입니다.")
    private String content;

    @NotBlank(message = "코스 지역은 필수값입니다.")
    @Size(max = 100, message = "코스 지역의 길이는 최대 100글자 입니다.")
    private String region;

    @NotNull(message = "공개 여부는 필수값입니다.")
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
    @NoArgsConstructor
    @AllArgsConstructor
    static public class TagInfo {

        @NotBlank(message = "태그는 비어있으면 안됩니다.")
        @Size(max = 10, message = "태그의 길이는 최대 10글자 입니다.")
        private String value;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static public class PlaceInfo {

        @NotNull(message = "장소 id는 필수값입니다.")
        @JsonProperty("id")
        private long kakaoId;

        @NotNull(message = "주소는 필수값입니다.")
        @JsonProperty("address_name")
        private String addressName;

        @NotNull(message = "카테고리 코드는 필수값입니다.")
        @JsonProperty("category_group_code")
        private String categoryGroupCode;

        @NotNull(message = "카테고리 이름은 필수값입니다.")
        @JsonProperty("category_group_name")
        private String categoryGroupName;

        @NotNull(message = "카테고리 Detail은 필수값입니다.")
        @JsonProperty("category_name")
        private String categoryName;

        @NotNull(message = "전화번호는 필수값입니다.")
        private String phone;

        @NotNull(message = "장소 이름은 필수값입니다.")
        @JsonProperty("place_name")
        private String placeName;

        @NotNull(message = "장소 URL은 필수값입니다.")
        @JsonProperty("place_url")
        private String placeUrl;

        @NotNull(message = "도로명 주소는 필수값입니다.")
        @JsonProperty("road_address_name")
        private String roadAddressName;

        @NotBlank(message = "경도값은 필수값입니다.")
        @JsonProperty("x")
        private String longitude;

        @NotBlank(message = "위도값은 필수값입니다.")
        @JsonProperty("y")
        private String latitude;

        @NotNull(message = "장소 순서는 필수값입니다.")
        @Min(value = 1, message = "장소 순서의 최소값은 1입니다.")
        @Max(value = 5, message = "장소 순서의 최대값은 5입니다.")
        private int index;
    }

    @JsonProperty("path")
    private List<Path> path;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static public class Path {

        @JsonProperty("lat")
        private double lat;

        @JsonProperty("log")
        private double log;

    }

    @JsonProperty("time")
    private Integer time;

    @JsonProperty("type")
    private String type;

    @JsonProperty("distance")
    private Integer distance;
}
