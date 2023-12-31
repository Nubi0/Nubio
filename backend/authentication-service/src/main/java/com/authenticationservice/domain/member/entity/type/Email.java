package com.authenticationservice.domain.member.entity.type;

import com.authenticationservice.domain.member.exception.InvalidEmailFormatException;
import com.authenticationservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Embeddable
public class Email {

    private static final Pattern PATTERN = Pattern.compile("\\w+@\\w+\\.\\w+(\\.\\w+)?");

    @Column(name = "email", nullable = false, unique = true)
    private String value;

    protected Email() {}

    private Email(final String value) {
        this.value = value;
    }

    public static Email from(final String value) {
        validate(value);
        return new Email(value);
    }

    private static void validate(final String email) {
        if (!PATTERN.matcher(email).matches()) {
            throw new InvalidEmailFormatException(ErrorCode.INVALID_EMAIL_FORMAT);
        }
    }

    private void setValue(String value) {
        this.value = value;
    }

    public void withdrawEmail() {
         this.setValue(this.value + UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;
        return Objects.equals(getValue(), email.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
