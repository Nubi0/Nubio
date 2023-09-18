package com.enjoyservice.global.error.exception;

import com.enjoyservice.global.error.ErrorCode;

public class FileException extends BusinessException{

    public FileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
