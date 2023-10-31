package com.chattingservice.domain.profile.exception;

import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.ValidationException;

public class InvalidFileSizeException extends ValidationException {

    public InvalidFileSizeException(ErrorCode errorCode) {
        super(errorCode);
    }
}

