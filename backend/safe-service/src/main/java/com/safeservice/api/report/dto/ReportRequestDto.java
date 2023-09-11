package com.safeservice.api.report.dto;

import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.entity.type.report.Active;
import com.safeservice.domain.report.entity.type.report.Content;
import com.safeservice.domain.report.entity.type.report.Position;
import com.safeservice.domain.report.entity.type.report.Title;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDto {

    @Length(max = 200, message = "댓글은 200자 이하여야 합니다.")
    @NotEmpty(message = "제목은 필수 값 입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수 값 입니다.")
    private String content;

    @NotEmpty(message = "제보 타입은 필수 값 입니다.")
    private String reportType;

    @NotEmpty(message = "위도는 필수 값 입니다.")
    private double latitude;

    @NotEmpty(message = "경도는 필수 값 입니다.")
    private double longitude;


    public static ReportRequestDto of(String title, String content, String reportType, double latitude, double longitude) {
        return ReportRequestDto.builder()
                .title(title)
                .content(content)
                .reportType(reportType)
                .latitude(latitude)
                .longitude(longitude).build();
    }

    public static Report toEntity(ReportRequestDto reportRequestDto, String identification) {
        return Report.builder()
                .title(Title.from(reportRequestDto.getTitle()))
                .content(Content.from(reportRequestDto.getContent()))
                .reportType(ReportType.from(reportRequestDto.getReportType()))
                .position(Position.of(reportRequestDto.getLongitude(), reportRequestDto.getLatitude()))
                .active(Active.from(true))
                .identification(identification).build();
    }
}
