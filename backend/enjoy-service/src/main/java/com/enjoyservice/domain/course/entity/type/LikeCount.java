package com.enjoyservice.domain.course.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeCount {

    @Column(name = "like_count")
    private long value;

    private LikeCount(final long value) {
        this.value = value;
    }

    public static LikeCount from(final long value) {
        return new LikeCount(value);
    }

    public void updateValue(long value) {
        setValue(value);
    }

    private void setValue(long value) {
        this.value = value;
    }
}
