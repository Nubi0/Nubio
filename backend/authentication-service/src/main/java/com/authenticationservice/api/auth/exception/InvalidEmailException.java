package com.authenticationservice.api.auth.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;
import com.authenticationservice.global.error.exception.InvalidException;
import com.authenticationservice.global.error.exception.ValidationException;

public class InvalidEmailException extends InvalidException {

    public InvalidEmailException(ErrorCode errorCode) {
        super(errorCode);
    }

}
