package com.enjoyservice.domain.tag.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Name {

    @Column(name = "name")
    private String value;

    private Name(final String value) {
        this.value = value;
    }

    public static Name from(final String value) {
        return new Name(value);
    }
}
