package com.safeservice.api.report.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportFileDto {

    private String url;

    public ReportFileDto(String url) {
        this.url = url;
    }
}
