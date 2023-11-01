package com.safeservice.api.report.dto;

import com.safeservice.domain.report.entity.ReportFile;
import com.safeservice.domain.report.entity.constant.reportfile.ReportFileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportFileResponseDto {

    private List<String> fileUrls;

    public static ReportFileResponseDto from(List<ReportFile> reportFiles) {
        return ReportFileResponseDto.builder()
                .fileUrls(reportFiles.stream()
                        .filter(reportFile -> reportFile.getReportFileType() == ReportFileType.RESIZE)
                        .map(reportFile -> reportFile.getFileUrl().getValue())
                        .collect(Collectors.toList())).build();
    }

}
