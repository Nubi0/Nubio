package com.chattingservice.domain.chatting.entity.constant;

import com.chattingservice.domain.chattingroom.excption.InvalidRoomTypeException;
import com.chattingservice.global.error.ErrorCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum MessageType {
    ENTER("ENTER"),
    TEXT("TEXT"),
    IMG("IMG"),
    ;

    private String description;

    MessageType(String description) {
        this.description = description;
    }

    public static MessageType from(String role) {
        validate(role);
        return MessageType.valueOf(role.toUpperCase());
    }

    public static boolean isMessage(String role) {
        List<MessageType> roles = Arrays.stream(MessageType.values())
                .filter(r -> r.name().equals(role))
                .collect(Collectors.toList());

        return roles.size() != 0;
    }

    private static void validate(String role) {
        if (!MessageType.isMessage(role.toUpperCase())) {
            throw new InvalidRoomTypeException(ErrorCode.INVALID_MESSAGE_TYPE);
        }
    }

}
