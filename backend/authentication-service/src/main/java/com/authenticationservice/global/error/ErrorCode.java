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
    EMAIL_IS_EXISTS(HttpStatus.BAD_REQUEST, "M-009", "존재하는 이메일입니다."),

    // 인증
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-001", "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-002", "해당 토큰은 유효한 토큰이 아닙니다."),
    EMAIL_CONFIRM_FAILED(HttpStatus.BAD_REQUEST, "A-003", "이메일 인증 실패하였습니다."),
    NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-004", "Authorization Header가 빈값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-005", "인증 타입이 Bearer 타입이 아닙니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A-006", "해당 refresh token은 존재하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-007", "해당 refresh token은 만료됐습니다."),
    NOT_ACCESS_TOKEN_TYPE(HttpStatus.UNAUTHORIZED, "A-008", "access-token이 아닙니다."),
    FORBIDDEN_ADMIN(HttpStatus.UNAUTHORIZED, "A-009", "ADMIN이 아닙니다."),

    // 프로필
    PROFILE_SIZE_LIMIT(HttpStatus.BAD_REQUEST, "P-003", "제출할 수 있는 파일 사이즈를 초과했습니다."),
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
