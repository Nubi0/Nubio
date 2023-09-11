package com.safeservice.domain.report.exception;

import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.ValidationException;

public class InvalidFileSizeException extends ValidationException {

    public InvalidFileSizeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
