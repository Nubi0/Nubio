package com.authenticationservice.global.error.exception;

import com.authenticationservice.global.error.ErrorCode;

public class InvalidException extends BusinessException{
    public InvalidException(ErrorCode errorCode) {
        super(errorCode);
    }
}
