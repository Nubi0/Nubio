package com.safeservice.api.emergencymessage.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataApiDto {

    @JsonProperty("row")
    private List<Row> row;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Row {

        @SerializedName("create_date")
        private String createDate;

        @SerializedName("location_id")
        private String locationId;

        @SerializedName("location_name")
        private String locationName;

        @SerializedName("md101_sn")
        private Integer md101Sn;

        @SerializedName("msg")
        private String msg;

        @SerializedName("send_platform")
        private String sendPlatform;
    }
}
