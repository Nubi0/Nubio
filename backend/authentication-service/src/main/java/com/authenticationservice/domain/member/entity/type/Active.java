package com.authenticationservice.domain.member.entity.type.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Active {

    @Column(name = "active")
    private boolean value = true;

    private Active(final boolean value) {
        this.value = value;
    }

    public static Active from(final boolean value) {
        return new Active(value);
    }

    private void setValue(boolean value) {
        this.value = value;
    }

    public void withdrawActive() {
        this.setValue(false);
    }

    public boolean getValue() {
        return value;
    }
}
