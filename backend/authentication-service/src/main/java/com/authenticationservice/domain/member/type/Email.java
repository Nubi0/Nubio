package com.authenticationservice.domain.member.type;

import com.authenticationservice.domain.member.exception.InvalidEmailFormatException;
import com.authenticationservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Email {

    private static final Pattern PATTERN = Pattern.compile("^[a-z]{1}[a-z0-9_\\.]+@[a-z\\.]+\\.[a-zA-Z]+$");

    @Column(name = "email", nullable = false)
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
            throw new InvalidEmailFormatException(ErrorCode.Invalid_Email_Format);
        }
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
