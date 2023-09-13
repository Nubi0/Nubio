package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Latitude {

    @Column(name = "latitude")
    private Double value;

    private Latitude(final double value) {
        this.value = value;
    }

    public static Latitude from(final double value) {
        return new Latitude(value);
    }
}
