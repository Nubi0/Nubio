package com.safeservice.api.emergencymessage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.api.emergencymessage.dto.client.DataApiDto;
import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.emergencymessage.entity.constant.EmerStage;
import com.safeservice.domain.emergencymessage.entity.constant.EmerType;
import com.safeservice.domain.emergencymessage.entity.type.Address;
import com.safeservice.domain.emergencymessage.entity.type.MdId;
import com.safeservice.domain.emergencymessage.entity.type.Message;
import com.safeservice.domain.emergencymessage.entity.type.OccurredTime;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EMRequestDto {

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

    public static EmergencyMessage toEntity(EMRequestDto emRequestDto) {
        return EmergencyMessage.builder()
                .mdId(MdId.from(emRequestDto.getMdId()))
                .emerType(EmerType.from(emRequestDto.getEmerType()))
                .emerStage(EmerStage.from(emRequestDto.getEmerStage()))
                .address(Address.of(emRequestDto.getCity(), emRequestDto.getCounty()))
                .message(Message.from(emRequestDto.getMessage()))
                .occurredTime(OccurredTime.from(toUTCTime(emRequestDto.getOccurredTime()))).build();
    }

    public static EmergencyMessage toEntityByApi(DataApiDto.DisasterMsg.Row row) {
        String[] locations = row.getLocationName().split(" ");
        return EmergencyMessage.builder()
                .mdId(MdId.from(row.getMd101Sn()))
                .emerType(EmerType.ETC)
                .emerStage(EmerStage.INFORMATION)
                .address(Address.of(locations[0], locations[1]))
                .message(Message.from(row.getMsg()))
                .occurredTime(OccurredTime.from(toUTCTime(row.getCreateDate()))).build();
    }

    private static LocalDateTime toUTCTime(LocalDateTime time) {
        return time.minus(9, ChronoUnit.HOURS);
    }
}
