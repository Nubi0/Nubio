package com.safeservice.api.path.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.path.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearNodeResponse {

    @JsonProperty("location")
    private Location location;

    @JsonProperty("safety_score")
    private Integer safetyScore;

    public static NearNodeResponse of(Node node) {
        return NearNodeResponse.builder()
                .location(Location.from(node.getLocation()))
                .safetyScore(node.getSafetyScore())
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Location{
        @JsonProperty("longitude")
        private double longitude;

        @JsonProperty("latitude")
        private double latitude;

        public static Location from(Point point) {
            return Location.builder()
                    .longitude(point.getX())
                    .latitude(point.getY())
                    .build();
        }

    }

}
