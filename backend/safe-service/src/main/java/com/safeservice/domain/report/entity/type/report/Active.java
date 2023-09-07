package com.safeservice.domain.report.entity.type.report;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Active {

    @Column(name = "active")
    private boolean value;

    private Active(final boolean value) {
        this.value = value;
    }

    public static Active from(final boolean value) {
        return new Active(value);
    }
}