package com.safeservice.domain.report.entity.type.reportfile;

import com.safeservice.domain.report.exception.InvalidFileSizeException;
import com.safeservice.domain.report.exception.InvalidTitleLengthException;
import com.safeservice.global.error.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileSize {

    @Column(name = "file_size", nullable = false)
    private Long value;

    private FileSize(final Long value) {
        this.value = value;
    }

    public static FileSize from(final Long value) {
        validate(value);
        return new FileSize(value);
    }

    private static void validate(final Long value) {
        if (value > 1000000) {
            throw new InvalidFileSizeException(ErrorCode.FILE_SIZE_LIMIT);
        }
    }
}
