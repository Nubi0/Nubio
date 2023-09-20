package com.safeservice.api.path.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NodeBetweenStartAndEnd {

    @JsonProperty("start_location")
    private Location start_location;

    @JsonProperty("end_location")
    private Location end_location;

    @Getter
    @NoArgsConstructor
    public static class Location{
        @JsonProperty("longitude")
        @Range(min = 120, max = 150, message = "경도가 범위를 벗어났습니다.")
        private double longitude;

        @JsonProperty("latitude")
        @Range(min = 20, max = 45, message = "위도가 범위를 벗어났습니다.")
        private double latitude;
    }

}
