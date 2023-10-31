package com.chattingservice.global.error.exception;

import com.chattingservice.global.error.ErrorCode;

public class ValidationException extends BusinessException {
    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
