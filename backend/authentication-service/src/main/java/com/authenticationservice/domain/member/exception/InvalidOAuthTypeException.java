package com.authenticationservice.domain.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;

public class InvalidOAuthTypeException extends BusinessException {

    public InvalidOAuthTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
