package com.enjoyservice.domain.place.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidGroupNameException extends ValidationException {

    public InvalidGroupNameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
