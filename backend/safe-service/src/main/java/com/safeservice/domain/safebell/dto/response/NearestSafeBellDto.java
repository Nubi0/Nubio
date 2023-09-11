package com.safeservice.domain.safebell.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NearestSafeBellDto {

    private double  longitude;
    private double  latitude;
    private String address;
    private double distance;

}
