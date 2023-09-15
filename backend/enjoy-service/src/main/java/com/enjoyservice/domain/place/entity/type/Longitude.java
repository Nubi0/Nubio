package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Longitude {

    @Column(name = "longitude")
    private Double value;

    private Longitude(final double value) {
        this.value = value;
    }

    public static Longitude from(final double value) {
        return new Longitude(value);
    }
}
