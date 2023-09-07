package com.authenticationservice.domain.member.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Nickname {

    @Column(name = "nickname", nullable = false)
    private String value;

    private Nickname(final String value) {
        this.value = value;
    }

    public static Nickname from(final String value) {
        return new Nickname(value);
    }

    public String getValue() {
        return value;
    }


}
