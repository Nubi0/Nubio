package com.safeservice.domain.emergencymessage.entity.constant;

import com.safeservice.domain.emergencymessage.exception.InvalidEmerStageException;
import com.safeservice.global.error.ErrorCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum EmerStage {


    INFORMATION("안전안내");

    private String description;

    EmerStage(String description) {
        this.description = description;
    }

    public static EmerStage from(String description) {
        return Arrays.stream(EmerStage.values())
                .filter(stage -> stage.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new InvalidEmerStageException(ErrorCode.INVALID_EMER_STAGE));
    }
}
