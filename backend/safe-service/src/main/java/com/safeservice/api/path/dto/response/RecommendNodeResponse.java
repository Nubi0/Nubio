package com.safeservice.api.path.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.path.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendNodeResponse {

    @JsonProperty("content")
    private List<NearNodeInfo> content = new ArrayList<>();

    public void add(Node node, List<SafetyFacility> facilities){
        content.add(NearNodeInfo.of(node, facilities));
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NearNodeInfo {

        @JsonProperty("location")
        private Location location;

        @JsonProperty("safety_score")
        private Integer safetyScore;

        @JsonProperty("safety_facilities")
        private List<SafetyFacilityInfo> safetyFacilities;

        public static NearNodeInfo of(Node node, List<SafetyFacility> facilities) {
            return NearNodeInfo.builder()
                    .location(Location.from(node.getLocation()))
                    .safetyScore(node.getSafetyScore())
                    .safetyFacilities(facilities.stream()
                            .map(facility -> SafetyFacilityInfo.of(facility))
                            .collect(Collectors.toList())
                    ).build();
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

        @Getter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class SafetyFacilityInfo {

            @JsonProperty("address")
            private String address;

            @JsonProperty("location")
            private Location location;

            @JsonProperty("facility_type")
            private FacilityType facilityType;

            public  static SafetyFacilityInfo of(SafetyFacility safetyFacility) {
                return SafetyFacilityInfo.builder()
                        .address(safetyFacility.getAddress())
                        .location(Location.from(safetyFacility.getLocation()))
                        .facilityType(safetyFacility.getFacilityType())
                        .build();
            }
        }

    }

}

