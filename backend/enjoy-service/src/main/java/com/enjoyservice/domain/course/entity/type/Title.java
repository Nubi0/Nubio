package com.enjoyservice.domain.course.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Title {

    @Column(name = "title", nullable = false)
    private String value;

    private Title(final String value) {
        this.value = value;
    }

    public static Title from(final String value) {
        return new Title(value);
    }
}
