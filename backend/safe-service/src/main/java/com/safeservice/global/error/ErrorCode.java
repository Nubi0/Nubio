package com.safeservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    TEST_EXCEPTION(HttpStatus.UNAUTHORIZED, "TEST-000", "에러 테스트"),

    // 대피소
    SHELTER_TYPE_NOT_EXIST(HttpStatus.BAD_REQUEST, "SH-001", "해당 보호소의 Type은 없습니다."),


    // 노드
    FACILITY__NOT_EXIST(HttpStatus.BAD_REQUEST, "N-001", "거리 이내에 안전시설물이 없습니다."),

    //안전시설
    FACILITY_TYPE_NOT_EXIST(HttpStatus.BAD_REQUEST, "F-001", "해당 시설물의 Type은 없습니다."),

    // 안전벨
    INVALID_ADDRESS(HttpStatus.BAD_REQUEST, "SB-001", "주소 길이가 잘못되었습니다."),
    NOTHING_SAFE_BELL(HttpStatus.BAD_REQUEST, "SB-002", "주변에 안전벨이 없습니다."),

    // 재난문자
    INVALID_EMER_TYPE(HttpStatus.BAD_REQUEST, "EM-001", "재난 타입이 잘못됐습니다."),
    INVALID_EMER_STAGE(HttpStatus.BAD_REQUEST, "EM-002", "긴급단계 타입이 잘못됐습니다."),

    // 제보
    INVALID_REPORT_TYPE(HttpStatus.BAD_REQUEST, "R-001", "제보 타입이 잘못됐습니다."),
    INVALID_REPORT_TITLE_LENGTH(HttpStatus.BAD_REQUEST, "R-002", "제목 길이 제한을 초과하였습니다."),
    INVALID_REPORT_CONTENT_LENGTH(HttpStatus.BAD_REQUEST, "R-003", "내용 길이 제한을 초과하였습니다."),
    MISMATCH_UPDATE_IDENTIFICATION(HttpStatus.BAD_REQUEST, "R-004", "작성자와 수정자가 동일하지 않습니다."),
    MISMATCH_DELETE_IDENTIFICATION(HttpStatus.BAD_REQUEST, "R-005", "작성자와 삭제 요청자가 동일하지 않습니다."),


    // File
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "F-001", "빈 파일은 제출할 수 없습니다."),
    FILE_AMOUNTS_LIMIT(HttpStatus.BAD_REQUEST, "F-002", "제출할 수 있는 파일 수를 초과했습니다."),
    FILE_SIZE_LIMIT(HttpStatus.BAD_REQUEST, "F-003", "제출할 수 있는 파일 사이즈를 초과했습니다."),
    FILE_FORMAT(HttpStatus.BAD_REQUEST, "F-004", "파일 형식이 잘못되었습니다."),
    INVALID_CSV_FORMAT(HttpStatus.BAD_REQUEST, "F-005", "csv 파일 형식이 잘못되었습니다."),
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
