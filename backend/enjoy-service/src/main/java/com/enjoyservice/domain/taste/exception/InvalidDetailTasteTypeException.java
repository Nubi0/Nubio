package com.enjoyservice.domain.taste.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidDetailTasteTypeException extends ValidationException {
    public InvalidDetailTasteTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
