package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Position {

    @Embedded
    private Longitude longitude;

    @Embedded
    private Latitude latitude;

    @Builder
    public Position(Longitude longitude, Latitude latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
