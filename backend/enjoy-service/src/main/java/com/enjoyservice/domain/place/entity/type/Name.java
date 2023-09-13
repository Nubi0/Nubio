package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {

    @Column(name = "name", nullable = false)
    private String value;

    private Name(final String value) {
        this.value = value;
    }

    public static Name from(final String value) {
        return new Name(value);
    }
}