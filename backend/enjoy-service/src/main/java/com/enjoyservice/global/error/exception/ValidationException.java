package com.enjoyservice.global.error.exception;

import com.enjoyservice.global.error.ErrorCode;

public class ValidationException extends BusinessException{

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
