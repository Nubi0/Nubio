package com.safeservice.domain.report.entity.type.report;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Position {

    @Column(nullable = false)
    private double longitude;
    @Column(nullable = false)
    private double latitude;

    private Position(final double longitude, final double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static Position of(final double longitude, final double latitude) {

        return new Position(longitude, latitude);
    }
}