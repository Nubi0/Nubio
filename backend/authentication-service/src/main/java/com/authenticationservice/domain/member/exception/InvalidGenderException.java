package com.authenticationservice.domain.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;

public class InvalidGenderException extends BusinessException {

    public InvalidGenderException(ErrorCode errorCode) {
        super(errorCode);
    }
}
