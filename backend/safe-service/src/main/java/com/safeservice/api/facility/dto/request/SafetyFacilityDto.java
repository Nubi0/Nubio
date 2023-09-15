package com.safeservice.api.facility.dto.request;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SafetyFacilityDto {

    @CsvBindByName(column = "ADDRESS")
    private String address;

    @CsvBindByName(column = "LON")
    private double longitude;

    @CsvBindByName(column = "LAT")
    private double latitude;

}
