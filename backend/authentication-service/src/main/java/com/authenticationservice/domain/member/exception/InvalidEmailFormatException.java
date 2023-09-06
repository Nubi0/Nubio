package com.authenticationservice.domain.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;
import com.authenticationservice.global.error.exception.ValidationException;

public class InvalidEmailFormatException extends ValidationException {

    public InvalidEmailFormatException(ErrorCode errorCode) {
        super(errorCode);
    }
}
