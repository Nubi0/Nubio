package com.enjoyservice.global.error.exception;

import com.enjoyservice.global.error.ErrorCode;

public class InvalidRecommendationException extends ValidationException{
    public InvalidRecommendationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
