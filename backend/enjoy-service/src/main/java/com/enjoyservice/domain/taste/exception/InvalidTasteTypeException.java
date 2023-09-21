package com.enjoyservice.domain.taste.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidTasteTypeException extends ValidationException {
    public InvalidTasteTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
