package com.safeservice.api.report.dto;

import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.entity.type.report.Active;
import com.safeservice.domain.report.entity.type.report.Content;
import com.safeservice.domain.report.entity.type.report.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDto {

    private String title;
    private String content;
    private String reportType;

    public static ReportRequestDto of(String title, String content, String reportType) {
        return ReportRequestDto.builder()
                .title(title)
                .content(content)
                .reportType(reportType).build();
    }

    public static Report toEntity(ReportRequestDto reportRequestDto, String identification) {
        return Report.builder()
                .title(Title.from(reportRequestDto.getTitle()))
                .content(Content.from(reportRequestDto.getContent()))
                .reportType(ReportType.from(reportRequestDto.getReportType()))
                .active(Active.from(true))
                .identification(identification).build();
    }
}
