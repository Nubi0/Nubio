package com.enjoyservice.domain.course.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Column(name = "content")
    @Lob
    private String value;

    private Content(final String value) {
        this.value = value;
    }

    public static Content from(final String value) {
        return new Content(value);
    }
}
