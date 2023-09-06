package com.authenticationservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 회원
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "M-001", "이메일 형식이 잘못됐습니다."),
    INVALID_OAUTHTYPE(HttpStatus.BAD_REQUEST, "M-002", "로그인 타입이 잘못됐습니다."),
    INVALID_ROLE(HttpStatus.BAD_REQUEST, "M-003", "회원 역할 타입이 잘못됐습니다."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST, "M-004", "성별 타입이 잘못됐습니다."),
    INVALID_BIRTH_FORMAT(HttpStatus.BAD_REQUEST, "M-005", "생년월일 형식이 잘못됐습니다.")
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
