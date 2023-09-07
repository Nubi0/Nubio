package com.safeservice.global.error.exception;

import com.safeservice.global.error.ErrorCode;


public class ValidationException extends BusinessException {

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
