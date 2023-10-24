package com.safeservice.api.chat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.api.emergencymessage.dto.EMInfoDto;
import com.safeservice.api.emergencymessage.dto.EMResponseDto;
import com.safeservice.api.emergencymessage.dto.client.ClientDto;
import com.safeservice.api.report.dto.ReportFileResponseDto;
import com.safeservice.api.report.dto.ReportListDto;
import com.safeservice.domain.emergencymessage.entity.EmergencyMessage;
import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.ReportFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatClientDto {

    private RegionDto regionDto;

    private EmergencyMessageDto emergencyMessageDto;

    private ReportDto reportDto;
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RegionDto {

        @JsonProperty("address_name")
        private String addressName;

        @JsonProperty("region_1depth_name")
        private String region1depthName;

        @JsonProperty("region_2depth_name")
        private String region2depthName;

        @JsonProperty("region_3depth_name")
        private String region3depthName;

        public static RegionDto from(ClientDto clientDto) {
            return RegionDto.builder()
                    .addressName(clientDto.getDocuments().get(0).getAddress_name())
                    .region1depthName(clientDto.getDocuments().get(0).getRegion_1depth_name())
                    .region2depthName(clientDto.getDocuments().get(0).getRegion_2depth_name())
                    .region3depthName(clientDto.getDocuments().get(0).getRegion_3depth_name()).build();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
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

        public static EmergencyMessageDto from(EmergencyMessage emergencyMessage) {
            return EmergencyMessageDto.builder()
                    .mdId(emergencyMessage.getMdId().getValue())
                    .emerType(emergencyMessage.getEmerType().getDescription())
                    .emerStage(emergencyMessage.getEmerStage().getDescription())
                    .city(emergencyMessage.getAddress().getCity())
                    .county(emergencyMessage.getAddress().getCounty())
                    .message(emergencyMessage.getMessage().getValue())
                    .occurredTime(toAsiaTime(emergencyMessage.getOccurredTime().getValue())).build();
        }

        private static LocalDateTime toAsiaTime(LocalDateTime time) {
            return time.plus(9, ChronoUnit.HOURS);
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
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
        public static class ReportFileDto {
            @JsonProperty("img_url")
            private List<String> imgUrl;

            public static ReportFileDto from(List<ReportFile> reportFile) {
                List<String> imgUrl = reportFile.stream()
                        .map(rf -> rf.getFileUrl().getValue())
                        .collect(Collectors.toList());
                return ReportFileDto.builder()
                        .imgUrl(imgUrl).build();
            }
        }

        public static ReportDto of(Report report) {
            return ReportDto.builder()
                    .reportId(report.getId())
                    .reportId(report.getId())
                    .title(report.getTitle().getValue())
                    .content(report.getContent().getValue())
                    .reportType(report.getReportType().getDescription())
                    .latitude(report.getPosition().getLatitude())
                    .longitude(report.getPosition().getLongitude())
                    .createTime(report.getCreateTime().plus(9, ChronoUnit.HOURS))
                    .reportFiles(ReportFileDto.from(report.getReportFiles())).build();
        }
    }
}

