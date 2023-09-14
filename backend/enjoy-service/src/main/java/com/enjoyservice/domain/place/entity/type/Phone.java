package com.enjoyservice.domain.place.entity.type;

import com.enjoyservice.domain.place.exception.InvalidPhoneNumberFormatException;
import com.enjoyservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Phone {

    private static final Pattern PHONE_NUMBER_PATTERN1 = Pattern.compile("^\\d{2,4}-\\d{3,4}-\\d{4}$");

    @Column(name = "phone_number")
    private String value;

    private Phone(final String value) {
        this.value = value;
    }

    public static Phone from(final String value) {
        validate(value);
        return new Phone(value);
    }

    private static void validate(final String value) {
        validateFormat(value);
    }

    private static void validateFormat(final String phoneNumber) {
        if (!PHONE_NUMBER_PATTERN1.matcher(phoneNumber).matches()) {
            throw new InvalidPhoneNumberFormatException(ErrorCode.INVALID_PHONE_NUMBER_FORMAT);
        }
    }
}
