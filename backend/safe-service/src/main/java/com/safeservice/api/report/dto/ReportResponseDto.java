package com.safeservice.api.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto {

    private List<ReportListDto> reportList;

    public static ReportResponseDto from(List<ReportListDto> reportList) {
        return ReportResponseDto.builder()
                .reportList(new ArrayList<>(reportList)).build();
    }
}
