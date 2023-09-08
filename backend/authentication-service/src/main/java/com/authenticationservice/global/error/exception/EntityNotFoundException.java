package com.authenticationservice.global.error.exception;

import com.authenticationservice.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
