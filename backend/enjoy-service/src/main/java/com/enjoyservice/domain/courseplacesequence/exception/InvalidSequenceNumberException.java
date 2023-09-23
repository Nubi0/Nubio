package com.enjoyservice.domain.courseplacesequence.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidSequenceNumberException extends ValidationException {

    public InvalidSequenceNumberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
