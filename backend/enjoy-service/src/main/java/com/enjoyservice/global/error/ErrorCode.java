package com.enjoyservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 장소
    PLACE_NOT_FOUND(HttpStatus.BAD_REQUEST, "S-001", "해당 장소는 존재하지 않습니다."),
    INVALID_GROUP_CODE(HttpStatus.BAD_REQUEST, "S-002", "해당 장소의 GroupCode는 존재하지 않습니다."),
    INVALID_GROUP_NAME(HttpStatus.BAD_REQUEST, "S-003", "해당 장소의 GroupName은 존재하지 않습니다."),
    INVALID_PHONE_NUMBER_FORMAT(HttpStatus.BAD_REQUEST, "S-004", "해당 PhoneNumber의 형식이 잘못되었습니다."),

    // 장소 리뷰
    INVALID_PLACE_REVIEW_POINT_RANGE(HttpStatus.BAD_REQUEST, "SR-001", "리뷰 점수의 범위는 0 ~ 5점 입니다."),

    // 코스
    INVALID_REGION(HttpStatus.BAD_REQUEST, "C-001", "해당 지역은 존재하지 않습니다."),
    INVALID_SEQUENCE_NUMBER_RANGE(HttpStatus.BAD_REQUEST, "C-002", "코스에 속한 장소의 순서값의 범위는 0 이상 입니다."),
    COURSE_NOT_FOUND(HttpStatus.BAD_REQUEST, "C-003", "해당 코스는 존재하지 않습니다."),
    INVALID_SERVICE_REGION(HttpStatus.BAD_REQUEST, "C-004", "해당 지역은 지원하는 지역이 아닙니다."),

    // 코스 리뷰
    INVALID_COURSE_REVIEW_POINT_RANGE(HttpStatus.BAD_REQUEST, "CR-001", "리뷰 점수의 범위는 0 ~ 5점 입니다."),

    // File
    INVALID_CSV_FORMAT(HttpStatus.BAD_REQUEST, "F-001", "csv 파일 형식이 잘못되었습니다."),
    FILE_FORMAT(HttpStatus.BAD_REQUEST, "F-002", "파일 형식이 잘못되었습니다."),
    COURSE_REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST, "F-003", "해당 코스 리뷰는 존재하지 않습니다."),

    // 취향
    INVALID_TASTE(HttpStatus.BAD_REQUEST,"T-001","해당 취향 목록은 존재하지 않습니다."),
    INVALID_DETAIL_TASTE(HttpStatus.BAD_REQUEST,"T-002","해당 취향 상세 목록은 존재하지 않습니다."),
    INVALID_CHECK_TASTE(HttpStatus.BAD_REQUEST,"T-003","해당 유저의 취향이 존재하지 않습니다."),

    // 코스 추천
    INVALID_RECOMMENDATION_LIST(HttpStatus.BAD_REQUEST,"R-001","적어도 하나의 취향을 선택하여야 합니다."),

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
