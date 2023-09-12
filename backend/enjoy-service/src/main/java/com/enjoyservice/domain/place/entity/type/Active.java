package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Active {

    @Column(name = "active", nullable = false)
    private boolean value = false;

    private Active(final boolean value) {
        this.value = value;
    }

    public static Active from(final boolean value) {
        return new Active(value);
    }
}
