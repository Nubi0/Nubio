package com.chattingservice.global.error.exception;

import com.chattingservice.global.error.ErrorCode;

public class InvalidMessageTypeException extends ValidationException {

    public InvalidMessageTypeException(ErrorCode errorCode) {
        super(errorCode);
    }

}
