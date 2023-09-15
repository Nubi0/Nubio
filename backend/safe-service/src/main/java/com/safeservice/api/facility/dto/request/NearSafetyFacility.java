package com.safeservice.api.facility.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearSafetyFacility {

    @JsonProperty("longitude")
    @Range(min = 120, max = 150, message = "경도가 범위를 벗어났습니다.")
    private double longitude;

    @JsonProperty("latitude")
    @Range(min = 20, max = 45, message = "위도가 범위를 벗어났습니다.")
    private double latitude;

    @JsonProperty("distance")
    @Range(min = 0, max = 50, message = "거리범위를 벗어났습니다.")
    private double distance;

}
