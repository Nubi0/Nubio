package com.enjoyservice.domain.placeimage.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Url {

    @Column(name = "url", length = 1500)
    private String value;

    private Url(final String value) {
        this.value = value;
    }

    public static Url from(final String value) {
        return new Url(value);
    }
}
