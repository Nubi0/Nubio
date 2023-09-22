package com.enjoyservice.domain.place.entity.constant;

import com.enjoyservice.domain.place.exception.InvalidGroupCodeException;
import com.enjoyservice.domain.place.exception.InvalidGroupNameException;
import com.enjoyservice.global.error.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GroupName {

    주유소("OL7"),
    편의점("CS2"),
    주차장("PK6"),
//    주유소충전소("주유소,충전소 코드"),
    문화시설("CT1"),
    관광명소("AT4"),
    숙박("AD5"),
    음식점("FD6"),
    카페("CE7"),
    ;

    private String description;

    GroupName(String description) {
        this.description = description;
    }

    public static GroupName from(String groupName) {
        validate(groupName);
        return GroupName.valueOf(groupName);
    }

    public static boolean isGroupName(String groupName) {
        List<GroupName> names = Arrays.stream(GroupName.values())
                .filter(g -> g.name().equals(groupName))
                .toList();

        return names.size() != 0;
    }

    private static void validate(String groupName) {
        if(!GroupName.isGroupName(groupName)) {
            throw new InvalidGroupNameException(ErrorCode.INVALID_GROUP_NAME);
        }
    }
}
