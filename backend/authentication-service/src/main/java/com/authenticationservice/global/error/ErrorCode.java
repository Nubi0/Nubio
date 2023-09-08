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
    INVALID_BIRTH_FORMAT(HttpStatus.BAD_REQUEST, "M-005", "생년월일 형식이 잘못됐습니다."),
    DUPLICATE_MEMBER_EXIST(HttpStatus.BAD_REQUEST, "M-006", "중복된 회원가입 입니다."),
    MEMBER_NOT_EXISTS(HttpStatus.BAD_REQUEST, "M-007", "해당 회원은 존재하지 않습니다."),
    INVALID_PASSWORD_CHECK(HttpStatus.BAD_REQUEST, "M-008", "비밀번호가 일치하지 않습니다."),

    // 인증
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),

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
