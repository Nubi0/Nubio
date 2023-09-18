package com.enjoyservice.domain.placelike.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Active {

    @Column(name = "active", nullable = false)
    private boolean value = false;

    private Active(final boolean value) {
        this.value = value;
    }

    public static com.enjoyservice.domain.placelike.entity.type.Active from(final boolean value) {
        return new com.enjoyservice.domain.placelike.entity.type.Active(value);
    }

    public boolean changeValue() {
        this.value = !this.value;
        return this.value;
    }
}
