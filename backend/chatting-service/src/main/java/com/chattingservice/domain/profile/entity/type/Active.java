package com.chattingservice.domain.profile.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
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