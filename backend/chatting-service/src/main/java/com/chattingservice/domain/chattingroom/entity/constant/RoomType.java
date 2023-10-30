package com.chattingservice.domain.chattingroom.entity.constant;

import com.chattingservice.domain.chattingroom.excption.InvalidRoomTypeException;
import com.chattingservice.global.error.ErrorCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoomType {


    DM("DM"),
    GROUP("GROUP"),
    ;
    private String description;

    RoomType(String description) {
        this.description = description;
    }

    public static RoomType from(String role) {
        validate(role);
        return RoomType.valueOf(role.toUpperCase());
    }

    public static boolean isRole(String role) {
        List<RoomType> roles = Arrays.stream(RoomType.values())
                .filter(r -> r.name().equals(role))
                .collect(Collectors.toList());

        return roles.size() != 0;
    }

    private static void validate(String role) {
        if(!RoomType.isRole(role.toUpperCase())) {
            throw new InvalidRoomTypeException(ErrorCode.INVALID_ROOM_TYPE);
        }
    }

}
