package com.safeservice.api.emergencymessage.dto.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataApiDto {

    @JsonProperty("DisasterMsg")
    private List<DisasterMsg> disasterMsg;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DisasterMsg {

        @JsonProperty("head")
        private Head head;


        @JsonProperty("row")
        private List<Row> row;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Head {
            @JsonProperty("totalCount")
            private int totalCount;

            @JsonProperty("numOfRows")
            private String numOfRows;

            @JsonProperty("pageNo")
            private String pageNo;

            @JsonProperty("type")
            private String type;

            @JsonProperty("RESULT")
            private Result result;

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Result {

                @JsonProperty("resultCode")
                private String resultCode;

                @JsonProperty("resultMsg")
                private String resultMsg;
            }

        }


        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Row {
            @JsonProperty("create_date")
            @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "Asia/Seoul")
            private LocalDateTime createDate;

            @JsonProperty("location_id")
            private String locationId;

            @JsonProperty("location_name")
            private String locationName;

            @JsonProperty("md101_sn")
            private Integer md101Sn;

            @JsonProperty("msg")
            private String msg;

            @JsonProperty("send_platform")
            private String sendPlatform;
        }
    }
}
