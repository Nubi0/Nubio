package com.safeservice.api.safetybell.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SafeBellDto {

    @CsvBindByName(column = "ADDRESS")
    private String address;

    @CsvBindByName(column = "LON")
    private double longitude;

    @CsvBindByName(column = "LAT")
    private double latitude;

}
