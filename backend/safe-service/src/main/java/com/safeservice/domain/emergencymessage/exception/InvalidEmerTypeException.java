package com.safeservice.domain.emergencymessage.exception;

import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.ValidationException;

public class InvalidEmerTypeException extends ValidationException {
    public InvalidEmerTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
