package com.enjoyservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 장소
    SPACE_NOT_EXISTS(HttpStatus.BAD_REQUEST, "S-001", "해당 장소는 존재하지 않습니다."),
    INVALID_GROUP_CODE(HttpStatus.BAD_REQUEST, "S-002", "해당 장소의 GroupCode는 존재하지 않습니다.")
    ;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}
