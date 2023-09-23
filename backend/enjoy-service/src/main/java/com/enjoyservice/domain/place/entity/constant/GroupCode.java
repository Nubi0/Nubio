package com.enjoyservice.domain.place.entity.constant;

import com.enjoyservice.domain.place.exception.InvalidGroupCodeException;
import com.enjoyservice.global.error.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GroupCode {

    CS2("편의점 코드"),
    PK6("주차장 코드"),
    OL7("주유소,충전소 코드"),
    CT1("문화시설 코드"),
    AT4("관광명소 코드"),
    AD5("숙박 코드"),
    FD6("음식점 코드"),
    CE7("카페 코드"),
    ;

    private String description;

    GroupCode(String description) {
        this.description = description;
    }

    public static GroupCode from(String groupCode) {
        validate(groupCode);
        return GroupCode.valueOf(groupCode.toUpperCase());
    }

    public static boolean isGroupCode(String groupCode) {
        List<GroupCode> codes = Arrays.stream(GroupCode.values())
                .filter(g -> g.name().equals(groupCode))
                .collect(Collectors.toList());

        return codes.size() != 0;
    }

    private static void validate(String groupCode) {
        if(!GroupCode.isGroupCode(groupCode.toUpperCase())) {
            throw new InvalidGroupCodeException(ErrorCode.INVALID_GROUP_CODE);
        }
    }
}
