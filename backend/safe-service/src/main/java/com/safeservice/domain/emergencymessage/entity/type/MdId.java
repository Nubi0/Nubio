package com.safeservice.domain.emergencymessage.entity.type;

import com.safeservice.domain.report.entity.type.report.Content;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MdId {

    @Column(name = "md_id", nullable = false)
    private Integer value;

    private MdId(final Integer value) {
        this.value = value;
    }

    public static MdId from(final Integer value) {
        return new MdId(value);
    }
}
