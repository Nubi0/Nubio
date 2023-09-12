package com.safeservice.api.report.dto;

import com.safeservice.domain.report.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportListDto {

    private boolean identificationFlag;

    private String title;

    private String content;

    private String reportType;

    private double latitude;

    private double longitude;

    private ReportFileResponseDto fileList;

    public static ReportListDto of(Report report, String identification) {
        return ReportListDto.builder()
                .identificationFlag(isMatchIdentification(report, identification))
                .title(report.getTitle().getValue())
                .content(report.getContent().getValue())
                .reportType(report.getReportType().getDescription())
                .latitude(report.getPosition().getLatitude())
                .longitude(report.getPosition().getLongitude())
                .fileList(ReportFileResponseDto.from(report.getReportFiles())).build();
    }

    private static boolean isMatchIdentification(Report report, String identification) {
        if (report.getIdentification().equals(identification)){
            return true;
        }
        return false;
    }
}
