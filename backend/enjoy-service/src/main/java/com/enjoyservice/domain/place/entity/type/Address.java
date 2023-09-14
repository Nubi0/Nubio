package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    @Column(name = "address_name")
    private String name;

    @Embedded
    private RoadName roadName;

    @Builder
    public Address(String name, RoadName roadName) {
        this.name = name;
        this.roadName = roadName;
    }
}
