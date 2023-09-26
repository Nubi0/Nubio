package com.safeservice.api.emergencymessage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EMInfoDto {

    @JsonProperty("md_id")
    private Integer mdId;

    @JsonProperty("emer_type")
    private String emerType;

    @JsonProperty("emer_stage")
    private String emerStage;

    private String city;

    private String county;

    private String message;

    @JsonProperty("occurred_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime occurredTime;

    public static EMInfoDto from(EmergencyMessage emergencyMessage) {
        return EMInfoDto.builder()
                .mdId(emergencyMessage.getMdId().getValue())
                .emerType(emergencyMessage.getEmerType().getDescription())
                .emerStage(emergencyMessage.getEmerStage().getDescription())
                .city(emergencyMessage.getAddress().getCity())
                .county(emergencyMessage.getAddress().getCounty())
                .message(emergencyMessage.getMessage().getValue())
                .occurredTime(emergencyMessage.getOccurredTime().getValue()).build();
    }
}
