package com.chattingservice.domain.notice.exception;

import com.chattingservice.global.error.ErrorCode;
import com.chattingservice.global.error.exception.ValidationException;

public class InvalidEmerTypeException extends ValidationException {
    public InvalidEmerTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}