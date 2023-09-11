package com.safeservice.domain.safebell.type;

import com.safeservice.domain.safebell.exception.InvalidAddressFormatException;
import com.safeservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private static final int MAX_LENGTH = 255;

    @Column(name = "address", nullable = false)
    String value;

    private Address(final String value) {
        this.value = value;
    }

    public static Address from(final String value) {
        validate(value);
        return new Address(value);
    }

    private static void validate(final String address) {
        if (address.length() > MAX_LENGTH) {
            throw new InvalidAddressFormatException(ErrorCode.INVALID_ADDRESS);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(value, address.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
