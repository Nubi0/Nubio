package com.safeservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TEST_EXCEPTION(HttpStatus.UNAUTHORIZED, "TEST-000", "에러 테스트"),


    // 안전벨
    INVALID_ADDRESS(HttpStatus.BAD_REQUEST, "SB-001", "주소 길이가 잘못되었습니다."),
    NOTHING_SAFE_BELL(HttpStatus.BAD_REQUEST, "SB-002", "주변에 안전벨이 없습니다."),



    // 제보
    INVALID_REPORT_TYPE(HttpStatus.BAD_REQUEST, "R-001", "제보 타입이 잘못됐습니다."),
    INVALID_REPORT_TITLE_LENGTH(HttpStatus.BAD_REQUEST, "R-002", "제목 길이 제한을 초과하였습니다."),
    INVALID_REPORT_CONTENT_LENGTH(HttpStatus.BAD_REQUEST, "R-003", "내용 길이 제한을 초과하였습니다."),
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
