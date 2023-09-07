package com.authenticationservice.global.error.exception;

import com.authenticationservice.global.error.ErrorCode;

public class ValidationException extends BusinessException{

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
