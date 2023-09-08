package com.safeservice.domain.report.exception;

import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.ValidationException;

public class InvalidTitleLengthException extends ValidationException {

    public InvalidTitleLengthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
