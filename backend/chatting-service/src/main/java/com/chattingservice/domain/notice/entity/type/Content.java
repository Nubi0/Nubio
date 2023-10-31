package com.chattingservice.domain.notice.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Column(name = "content", nullable = false)
    @Lob
    private String value;

    private Content(final String value) {
        this.value = value;
    }

    public static Content from(final String value){
        return new Content(value);
    }

}
