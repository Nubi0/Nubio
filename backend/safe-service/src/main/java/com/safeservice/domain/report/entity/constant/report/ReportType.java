package com.safeservice.domain.report.entity.constant.report;

import com.safeservice.domain.report.exception.InvalidReportTypeException;
import com.safeservice.global.error.ErrorCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum ReportType {

    TERROR("terror"),
    ACCIDENT("accident"),
    CONSTRUCTION("construction");

    private String description;

    ReportType(String description) {
        this.description = description;
    }

    public static ReportType from(String type) {
        validate(type);
        return ReportType.valueOf(type.toUpperCase());
    }

    public static boolean isRole(String type) {
        List<ReportType> types = Arrays.stream(ReportType.values())
                .filter(g -> g.name().equals(type))
                .collect(Collectors.toList());

        return types.size() != 0;
    }

    private static void validate(String type) {
        if(!ReportType.isRole(type.toUpperCase())) {
            throw new InvalidReportTypeException(ErrorCode.INVALID_REPORT_TYPE);
        }
    }
}
