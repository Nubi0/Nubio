package com.safeservice.domain.report.entity.type.report;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region {

    @Column(name = "region", nullable = false)
    private String value;

    private Region(final String value) {
        this.value = value;
    }

    public static Region from(final String value) {
        return new Region(value);
    }
}
