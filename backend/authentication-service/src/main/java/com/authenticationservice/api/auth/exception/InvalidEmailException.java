package com.authenticationservice.api.auth.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;

public class InvalidEmailException extends BusinessException {

    public InvalidEmailException(ErrorCode errorCode) {
        super(errorCode);
    }

}
