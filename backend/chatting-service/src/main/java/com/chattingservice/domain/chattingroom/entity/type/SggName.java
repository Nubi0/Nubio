package com.chattingservice.domain.chattingroom.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SggName {

    @Column(name = "sgg_name")
    private String name;

    @Builder
    public SggName(String name) {
        this.name = name;
    }

    public static SggName from(final String value) {
        return new SggName(value);
    }

}
