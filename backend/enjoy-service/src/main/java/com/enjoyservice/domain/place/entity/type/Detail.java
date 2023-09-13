package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Detail {

    @Column(name = "category_detail", nullable = false)
    private String value;

    private Detail(final String value) {
        this.value = value;
    }

    public static Detail from(final String value) {
        return new Detail(value);
    }
}
