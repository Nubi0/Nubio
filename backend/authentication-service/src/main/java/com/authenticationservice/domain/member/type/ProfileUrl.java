package com.authenticationservice.domain.member.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileUrl {

    @Column(name = "profile_url")
    private String value;

    private ProfileUrl(final String value) {
        this.value = value;
    }

    public static ProfileUrl from(final String value) {
        return new ProfileUrl(value);
    }

    public String getValue() {
        return value;
    }
}
