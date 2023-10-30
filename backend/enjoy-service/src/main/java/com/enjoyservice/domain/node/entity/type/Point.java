package com.enjoyservice.domain.node.entity.type;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {

    private double longitude;
    private double latitude;

    private Point(final double longitude, final double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static Point of(final double longitude, final double latitude) {
        return new Point(longitude, latitude);
    }
}
