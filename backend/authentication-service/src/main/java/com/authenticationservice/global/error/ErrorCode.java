package com.authenticationservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 회원
    Invalid_Email_Format(HttpStatus.BAD_REQUEST, "M-001", "이메일 형식이 잘못됐습니다."),
    Invalid_OAuthType(HttpStatus.BAD_REQUEST, "M-002", "로그인 타입이 잘못됐습니다.")
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
