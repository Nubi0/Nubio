package com.safeservice.api.report.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safeservice.domain.report.entity.Report;
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
public class ReportListDto {

    private Long reportId;

    private boolean identificationFlag;

    private String title;

    private String content;

    private String reportType;

    private double latitude;

    private double longitude;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private ReportFileResponseDto fileList;

    public static ReportListDto of(Report report, String identification) {
        return ReportListDto.builder()
                .reportId(report.getId())
                .identificationFlag(isMatchIdentification(report, identification))
                .title(report.getTitle().getValue())
                .content(report.getContent().getValue())
                .reportType(report.getReportType().getDescription())
                .latitude(report.getPosition().getLatitude())
                .longitude(report.getPosition().getLongitude())
                .createTime(report.getCreateTime().plus(9, ChronoUnit.HOURS))
                .fileList(ReportFileResponseDto.from(report.getReportFiles())).build();
    }

    private static boolean isMatchIdentification(Report report, String identification) {
        if (identification != null) {
            if (report.getIdentification().equals(identification)){
                return true;
            }
            return false;
        }
        return false;
    }
}
