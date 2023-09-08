package com.authenticationservice.api.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;

public class InvalidPasswordException extends BusinessException {

    public InvalidPasswordException(ErrorCode errorCode) {
        super(errorCode);
    }

}

