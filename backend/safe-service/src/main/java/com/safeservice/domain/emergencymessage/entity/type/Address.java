package com.safeservice.domain.emergencymessage.entity.type;

import com.safeservice.domain.report.entity.type.report.Position;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;
    private String county;

    private Address(final String city, final String county) {
        this.city = city;
        this.county = county;
    }

    public static Address of(final String city, final String county) {

        return new Address(city, county);
    }
}
