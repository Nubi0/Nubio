package com.enjoyservice.domain.course.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceInCourseInfoDto {
    private long id;
    private int kakaoId;
    private String addressName;
    private String categoryGroupCode;
    private String categoryGroupName;
    private String phone;
    private String placeName;
    private String placeUrl;
    private String roadAddressName;
    private String longitude;
    private String latitude;
    private int sequence;
}
