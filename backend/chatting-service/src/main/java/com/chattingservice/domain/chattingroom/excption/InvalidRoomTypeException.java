package com.chattingservice.domain.chattingroom.excption;

import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.ValidationException;

public class InvalidRoomTypeException extends ValidationException {

    public InvalidRoomTypeException(ErrorCode errorCode) {
        super(errorCode);
    }

}
