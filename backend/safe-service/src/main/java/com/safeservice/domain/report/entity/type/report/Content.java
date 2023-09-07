package com.safeservice.domain.report.entity.type.report;

import com.safeservice.domain.report.exception.InvalidTitleLengthException;
import com.safeservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Lob
    @Column(name = "content", nullable = false)
    private String value;

    private Content(String value) {
        this.value = value;
    }

    public static Content from(String value) {
        return new Content(value);
    }

}
