package com.enjoyservice.domain.place.exception;

import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.EntityNotFoundException;

public class PlaceNotFoundException extends EntityNotFoundException {

    public PlaceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
