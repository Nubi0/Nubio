package com.enjoyservice.domain.course.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PublicFlag {

    @Column(name = "public_flag", nullable = false)
    private boolean value = false;

    private PublicFlag(final boolean value) {
        this.value = value;
    }

    public static PublicFlag from(final boolean value) {
        return new PublicFlag(value);
    }
}
