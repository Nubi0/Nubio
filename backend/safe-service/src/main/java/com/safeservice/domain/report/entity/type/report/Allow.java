package com.safeservice.domain.report.entity.type.report;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Allow {
    @Column(name = "active")
    private boolean value;

    private Allow(final boolean value) {
        this.value = value;
    }

    public static Allow from(final boolean value) {
        return new Allow(value);
    }
}
