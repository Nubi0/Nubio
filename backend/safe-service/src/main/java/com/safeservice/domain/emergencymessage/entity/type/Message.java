package com.safeservice.domain.emergencymessage.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {

    @Column(name = "message", nullable = false)
    private String value;

    private Message(final String value) {
        this.value = value;
    }

    public static Message from(final String value) {
        return new Message(value);
    }
}
