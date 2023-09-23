package com.safeservice.api.facility.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearSafetyResponseDto {

    @JsonProperty("address")
    private String address;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("facility_type")
    private FacilityType facilityType;

    public static NearSafetyResponseDto of(SafetyFacility safetyFacility) {
        return NearSafetyResponseDto.builder()
                .address(safetyFacility.getAddress())
                .location(Location.from(safetyFacility.getLocation()))
                .facilityType(safetyFacility.getFacilityType())
                .build();
    }

}
