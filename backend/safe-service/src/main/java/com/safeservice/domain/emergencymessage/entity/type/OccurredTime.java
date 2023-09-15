package com.safeservice.domain.emergencymessage.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OccurredTime {

    @Column(name = "occurred_time", nullable = false)
    private LocalDateTime value;

    private OccurredTime(final LocalDateTime value) {
        this.value = value;
    }

    public static OccurredTime from(final LocalDateTime value) {
        return new OccurredTime(value);
    }
}
