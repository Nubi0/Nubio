package com.authenticationservice.external.email.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.ValidationException;

public class ValidEmailException extends ValidationException {
    public ValidEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
