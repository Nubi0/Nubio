package com.authenticationservice.domain.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;

public class InvalidEmailFormatException extends BusinessException {

    public InvalidEmailFormatException(ErrorCode errorCode) {
        super(errorCode);
    }
}
