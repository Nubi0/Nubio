package com.authenticationservice.domain.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;
import com.authenticationservice.global.error.exception.ValidationException;

public class InvalidOAuthTypeException extends ValidationException {

    public InvalidOAuthTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
