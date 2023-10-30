package com.chattingservice.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 채팅 방
    ROOM_NOT_EXIST(HttpStatus.BAD_REQUEST, "R-001", "해당 방번호는 없는 번호입니다."),
    ROOM_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "R-002", "채팅방이 없습니다."),
    ROOM_NOT_GROUP_ERROR(HttpStatus.BAD_REQUEST, "R-003", "그룸 채팅방이 아닙니다."),

    // 채팅
    CHAT_NOT_FOUND(HttpStatus.NOT_FOUND, "C-001", "해당 방에는 채팅이 존재하지 않습니다."),

    // 메시지
    INVALID_MESSAGE_TYPE_CODE(HttpStatus.BAD_REQUEST, "M-001", "해당 메시지 타입은 존재하지 않습니다."),

    // 프로필
    PROFILE_SIZE_LIMIT(HttpStatus.BAD_REQUEST, "Profile-003", "제출할 수 있는 파일 사이즈를 초과했습니다."),


    // 참석자
    INVALID_ROLE(HttpStatus.BAD_REQUEST, "Participant-001", "회원 역할 타입이 잘못됐습니다."),
    DUPLICATE_MEMBER_EXIST(HttpStatus.BAD_REQUEST, "Participant-002", "중복된 채팅방 가입 입니다."),
    PARTICIPANT_NOT_EXIST(HttpStatus.BAD_REQUEST, "Participant-003", "해당 회원번호는 없는 번호입니다"),

    // 재난공지
    INVALID_EMER_TYPE(HttpStatus.BAD_REQUEST, "EM-001", "재난 타입이 잘못됐습니다."),

    // 채팅방
    INVALID_ROOM_TYPE(HttpStatus.BAD_REQUEST, "CR-001", "채팅방 타입이 잘못됐습니다."),

    // File
    INVALID_CSV_FORMAT(HttpStatus.BAD_REQUEST, "F-001", "csv 파일 형식이 잘못되었습니다."),
    FILE_FORMAT(HttpStatus.BAD_REQUEST, "F-002", "파일 형식이 잘못되었습니다."),
    COURSE_REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST, "F-003", "해당 코스 리뷰는 존재하지 않습니다."),

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
