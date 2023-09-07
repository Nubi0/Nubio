package com.safeservice.domain.safebell.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Position {

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    private Position(final double longitude,final double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static Position from(final double longitude, final double latitude) {
        return new Position(longitude, latitude);
    }

}
