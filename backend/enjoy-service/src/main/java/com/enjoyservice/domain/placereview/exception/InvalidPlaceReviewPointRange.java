package com.enjoyservice.domain.placereview.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.ValidationException;

public class InvalidPlaceReviewPointRange extends ValidationException {

    public InvalidPlaceReviewPointRange(ErrorCode errorCode) {
        super(errorCode);
    }
}
