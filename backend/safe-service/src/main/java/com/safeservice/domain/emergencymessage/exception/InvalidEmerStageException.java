package com.safeservice.domain.emergencymessage.exception;

import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.ValidationException;

public class InvalidEmerStageException extends ValidationException {
    public InvalidEmerStageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
