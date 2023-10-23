package com.safeservice.domain.report.entity.constant.reportfile;

import lombok.Getter;

@Getter
public enum ReportFileType {
    ORIGIN("origin"),
    RESIZE("resize");

    private String description;

    ReportFileType(String description) {
        this.description = description;
    }

    public static ReportFileType from(String type) {
        return ReportFileType.valueOf(type.toUpperCase());
    }

}
