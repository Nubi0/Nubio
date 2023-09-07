package com.safeservice.domain.report.entity.type.reportfile;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileName {

    @Column(name = "filename", nullable = false)
    private String value;

    private FileName(final String value) {
        this.value = value;
    }

    public static FileName from(final String value) {
        return new FileName(value);
    }
}
