package com.chattingservice.api.chatting.client.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ChatClientDto {

    private RegionDto region;

    private EmergencyMessageDto emergency_message;

    private ReportDto report;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class RegionDto {

        @JsonProperty("address_name")
        private String addressName;

        @JsonProperty("region_1depth_name")
        private String region1depthName;

        @JsonProperty("region_2depth_name")
        private String region2depthName;

        @JsonProperty("region_3depth_name")
        private String region3depthName;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class EmergencyMessageDto {
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
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime occurredTime;


    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class ReportDto {
        @JsonProperty("report_id")
        private Long reportId;

        private String title;

        private String content;

        @JsonProperty("report_type")
        private String reportType;

        private double latitude;

        private double longitude;

        @JsonProperty("create_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;

        @JsonProperty("report_files")
        private ReportFileDto reportFiles;


        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @Getter
        public static class ReportFileDto {
            @JsonProperty("img_url")
            private List<String> imgUrl;

        }

    }

}