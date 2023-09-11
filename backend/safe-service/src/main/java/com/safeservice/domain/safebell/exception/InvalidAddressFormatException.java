package com.safeservice.domain.safebell.exception;

import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.ValidationException;

public class InvalidAddressFormatException  extends ValidationException {
    public InvalidAddressFormatException(ErrorCode errorCode) {
        super(errorCode);
    }
}
