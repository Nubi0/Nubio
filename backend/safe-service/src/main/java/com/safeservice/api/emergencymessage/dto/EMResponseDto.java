package com.safeservice.api.emergencymessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EMResponseDto {

    @JsonProperty("emergency_messages")
    private List<EMInfoDto> emergencyMessages;

    public static EMResponseDto from(List<EMInfoDto> emInfoDtos) {
        return EMResponseDto.builder()
                .emergencyMessages(new ArrayList<>(emInfoDtos)).build();
    }
}
