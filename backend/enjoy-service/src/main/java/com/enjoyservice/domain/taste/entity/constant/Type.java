package com.enjoyservice.domain.taste.entity.constant;

import com.enjoyservice.domain.taste.exception.InvalidTasteTypeException;
import com.enjoyservice.global.error.ErrorCode;
import lombok.Getter;
import java.util.Arrays;


@Getter
public enum Type {

    EAT("먹기"),
    PLAY("놀기"),
    DRINK("마시기");

    private String description;

    Type(String description) {
        this.description = description;
    }

    public static Type from(String description) {
        return Arrays.stream(Type.values())
                .filter(stage -> stage.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new InvalidTasteTypeException(ErrorCode.INVALID_TASTE));
    }
}
