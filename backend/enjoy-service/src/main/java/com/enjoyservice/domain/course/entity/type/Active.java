package com.enjoyservice.domain.course.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Active {

    @Column(name = "active", nullable = false)
    private boolean value = true;

    private Active(final boolean value) {
        this.value = value;
    }

    public static Active from(final boolean value) {
        return new Active(value);
    }
}
