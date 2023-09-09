package com.safeservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TEST_EXCEPTION(HttpStatus.UNAUTHORIZED, "TEST-000", "에러 테스트"),
    // 제보
    INVALID_REPORT_TYPE(HttpStatus.BAD_REQUEST, "R-001", "제보 타입이 잘못됐습니다."),
    INVALID_REPORT_TITLE_LENGTH(HttpStatus.BAD_REQUEST, "R-002", "제목 길이 제한을 초과하였습니다."),
    INVALID_REPORT_CONTENT_LENGTH(HttpStatus.BAD_REQUEST, "R-003", "내용 길이 제한을 초과하였습니다."),

    // File
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "F-001", "빈 파일은 제출할 수 없습니다."),
    FILE_AMOUNTS_LIMIT(HttpStatus.BAD_REQUEST, "F-002", "제출할 수 있는 파일 수를 초과했습니다."),
    FILE_SIZE_LIMIT(HttpStatus.BAD_REQUEST, "F-003", "제출할 수 있는 파일 사이즈를 초과했습니다."),
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
