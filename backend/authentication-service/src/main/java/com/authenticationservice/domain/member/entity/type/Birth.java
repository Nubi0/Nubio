package com.authenticationservice.domain.member.entity.type.member;

import com.authenticationservice.domain.member.exception.InvalidBirthFormatException;
import com.authenticationservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Birth {

    private static final Pattern PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    @Column(name = "birth")
    private LocalDate value;

    private Birth(LocalDate value) {
        this.value = value;
    }

    public static Birth from(final String value) {
        validate(value);
        return new Birth(Birth.convertToLocalDate(value));
    }

    private static void validate(final String birth) {
        if (!PATTERN.matcher(birth).matches()) {
            throw new InvalidBirthFormatException(ErrorCode.INVALID_BIRTH_FORMAT);
        }
    }

    private static LocalDate convertToLocalDate(final String value) {
        return LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
    }

    private void setValue(LocalDate value) {
        this.value = value;
    }

    public void withdrawBirth() {
        this.setValue(LocalDate.of(1000,1,1));
    }

    public LocalDate getValue() {
        return value;
    }

}
