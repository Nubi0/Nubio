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
import org.hibernate.validator.constraints.Range;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportUpdateRequestDto {

    @NotNull(message = "제보Id는 필수 값 입니다.")
    private Long reportId;

    @Length(max = 200, message = "댓글은 200자 이하여야 합니다.")
    @NotEmpty(message = "제목은 필수 값 입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수 값 입니다.")
    private String content;

    @NotEmpty(message = "제보 타입은 필수 값 입니다.")
    private String reportType;

    @Range(min = 20, max = 45, message = "위도가 범위를 벗어났습니다.")
    private double latitude;

    @Range(min = 120, max = 150, message = "경도가 범위를 벗어났습니다.")
    private double longitude;


    public static Report toEntity(ReportUpdateRequestDto reportUpdateRequestDto) {
        return Report.builder()
                .title(Title.from(reportUpdateRequestDto.getTitle()))
                .content(Content.from(reportUpdateRequestDto.getContent()))
                .reportType(ReportType.from(reportUpdateRequestDto.getReportType()))
                .position(Position.of(reportUpdateRequestDto.getLongitude(), reportUpdateRequestDto.getLatitude()))
                .active(Active.from(true)).build();
    }
}
