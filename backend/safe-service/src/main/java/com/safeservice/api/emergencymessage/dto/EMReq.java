package com.safeservice.api.emergencymessage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EMReq {

    @JsonProperty("data")
    private List<EMRequestDto> data;

    public static EMReq from(List<EMRequestDto> data) {
        return EMReq.builder()
                .data(data).build();
    }
}
