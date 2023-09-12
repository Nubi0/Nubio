package com.enjoyservice.domain.placeimage.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {

    @Column(name = "origin_name")
    private String value;

    private Name(final String value) {
        this.value = value;
    }

    public static Name from(final String value) {
        return new Name(value);
    }
}
