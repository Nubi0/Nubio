package com.safeservice.api.emergencymessage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EMAddressDto {

    private double longitude;
    private double latitude;

}
