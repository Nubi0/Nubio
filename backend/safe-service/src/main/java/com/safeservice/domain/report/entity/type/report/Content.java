package com.safeservice.domain.report.entity.type.report;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Column(name = "content", nullable = false)
    private String value;

    private Content(String value) {
        this.value = value;
    }

    public static Content from(String value) {
        return new Content(value);
    }
}
