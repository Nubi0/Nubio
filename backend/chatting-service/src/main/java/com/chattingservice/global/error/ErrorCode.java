package com.chattingservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 채팅 방
    ROOM_NOT_EXIST(HttpStatus.BAD_REQUEST, "R-001", "해당 방번호는 없는 번호입니다."),
    ROOM_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "R-002", "채팅방이 없습니다."),
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
