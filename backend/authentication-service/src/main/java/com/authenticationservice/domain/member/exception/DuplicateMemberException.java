package com.authenticationservice.domain.member.exception;

import com.authenticationservice.global.error.ErrorCode;
import com.authenticationservice.global.error.exception.BusinessException;

public class DuplicateMemberException extends BusinessException {

    public DuplicateMemberException(ErrorCode errorCode) {
        super(errorCode);
    }

}
