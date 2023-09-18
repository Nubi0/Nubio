package com.enjoyservice.domain.courseplacesequence.entity.type;

import com.enjoyservice.domain.courseplacesequence.entity.exception.InvalidSequenceNumberException;
import com.enjoyservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SequenceNumber {

    private static final int MIN_RANGE = 0;

    @Column(name = "sequence_number", nullable = false)
    private int value;

    private SequenceNumber(final int value) {
        this.value = value;
    }

    public static SequenceNumber from(final int value) {
        validate(value);
        return new SequenceNumber(value);
    }

    private static void validate(final int value) {
        validateRange(value);
    }

    private static void validateRange(final int value) {
        if(value < MIN_RANGE) {
            throw new InvalidSequenceNumberException(ErrorCode.INVALID_SEQUENCE_NUMBER_RANGE);
        }
    }
}
