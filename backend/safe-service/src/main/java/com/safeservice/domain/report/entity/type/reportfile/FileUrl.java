package com.safeservice.domain.report.entity.type.reportfile;

import com.safeservice.domain.report.entity.type.report.Title;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileUrl {

    @Column(name = "file_url", nullable = false)
    private String value;

    private FileUrl(final String value) {
        this.value = value;
    }

    public static FileUrl from(final String value) {
        return new FileUrl(value);
    }
}
