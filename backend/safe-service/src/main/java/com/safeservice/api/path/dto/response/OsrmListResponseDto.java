package com.safeservice.api.path.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OsrmListResponseDto {

    @JsonProperty("safe_facility")
    List<NearSafetyResponseDto> nearSafetyResponseDtoList;

    @JsonProperty("points")
    List<Location> points;

    @JsonProperty("duration")
    int duration;

    @JsonProperty("distance")
    double distance;

    public static OsrmListResponseDto of(List<NearSafetyResponseDto> nearSafetyResponseDtoList, List<Point> points, int duration, double distance) {
        return OsrmListResponseDto.builder()
                .nearSafetyResponseDtoList(nearSafetyResponseDtoList)
                .points(points.stream().map(p ->  Location.from(p)).collect(Collectors.toList()))
                .duration(duration)
                .distance(distance)
                .build();
    }

}
