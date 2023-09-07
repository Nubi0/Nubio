package com.safeservice.domain.report.entity.type.report;

import com.safeservice.domain.report.exception.InvalidTitleLengthException;
import com.safeservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Title {

    @Column(name = "title", nullable = false)
    private String value;

    private Title(final String value) {
        this.value = value;
    }

    public static Title from(final String value) {
        validate(value);
        return new Title(value);
    }

    private static void validate(final String value) {
        if (value.length() > 200) {
            throw new InvalidTitleLengthException(ErrorCode.INVALID_REPORT_TITLE_LENGTH);
        }
    }
}
