package com.safeservice.api.shelter.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.api.facility.dto.response.NearSafetyResponseDto;
import com.safeservice.domain.shelter.constant.ShelterType;
import com.safeservice.domain.shelter.entity.Shelter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearShelterResponseDto {

    @JsonProperty("address")
    private String address;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phone_number;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("shelter_type")
    private ShelterType shelterType;


    public static NearShelterResponseDto of(Shelter shelter) {
        return NearShelterResponseDto.builder()
                .address(shelter.getAddress())
                .name(shelter.getName())
                .phone_number(shelter.getPhoneNumber())
                .location(Location.from(shelter.getLocation()))
                .shelterType(shelter.getShelterType())
                .build();
    }
}
