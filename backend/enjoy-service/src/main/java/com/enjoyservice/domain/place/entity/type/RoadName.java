package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoadName {

    @Column(name = "road_address_name")
    private String value;

    private RoadName(final String value) {
        this.value = value;
    }

    public static RoadName from(final String value) {
        return new RoadName(value);
    }
}
