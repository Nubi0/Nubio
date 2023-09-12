package com.safeservice.domain.emergencymessage.entity.constant;

import com.safeservice.domain.report.entity.constant.report.ReportType;
import com.safeservice.domain.report.exception.InvalidReportTypeException;
import com.safeservice.global.error.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EmerType {

    ETC("기타"),
    ACCIDENT("accident"),
    CONSTRUCTION("construction");

    private String description;

    EmerType(String description) {
        this.description = description;
    }

    public static EmerType from(String type) {
        validate(type);
        return EmerType.valueOf(type);
    }

    public static boolean isRole(String type) {
        List<EmerType> types = Arrays.stream(EmerType.values())
                .filter(g -> g.name().equals(type))
                .collect(Collectors.toList());
        return types.size() != 0;
    }

    private static void validate(String type) {
        if(!EmerType.isRole(type)) {
            throw new InvalidReportTypeException(ErrorCode.INVALID_REPORT_TYPE);
        }
    }
}
