package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {

    @Column(name = "name", nullable = false)
    private String value;

    private Name(final String value) {
        this.value = value;
    }

    public static com.enjoyservice.domain.place.entity.type.Name from(final String value) {
        return new com.enjoyservice.domain.place.entity.type.Name(value);
    }
}
