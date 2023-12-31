package com.chattingservice.domain.participant.enity.type;

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
    private boolean value = true;

    private Active(final boolean value) {
        this.value = value;
    }

    public static Active from(final boolean value) {
        return new Active(value);
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void withdrawActive() {
        this.setValue(false);
    }

    public boolean getValue() {
        return value;
    }
}
