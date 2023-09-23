package com.enjoyservice.domain.taste.entity.constant;

import com.enjoyservice.domain.taste.exception.InvalidDetailTasteTypeException;
import com.enjoyservice.domain.taste.exception.InvalidTasteTypeException;
import com.enjoyservice.global.error.ErrorCode;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DetailType {

    KOREAN("한식"),
    WESTERN("양식"),
    CHINESE("중식"),
    JAPANESE("일식"),

    MOVIE("영화"),
    BOARD_GAME("보드게임"),
    KARAOKE("노래방"),
    THEME_PARK("테마파크"),
    BEACH("해수욕장"),
    CULTURAL_HERITAGE("문화재"),
    ISLAND("섬"),

    ALCOHOL("술"),
    COFFEE("커피"),
    ICE_CREAM("아이스크림")
    ;

    private String description;

    DetailType(String description) {
        this.description = description;
    }

    public static DetailType from(String description) {
        return Arrays.stream(DetailType.values())
                .filter(stage -> stage.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new InvalidDetailTasteTypeException(ErrorCode.INVALID_DETAIL_TASTE));
    }
}
