package com.authenticationservice.domain.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;

public class InvalidRoleException extends BusinessException {

    public InvalidRoleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
