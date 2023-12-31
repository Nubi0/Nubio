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
public class SidoName {

    @Column(name = "sido_name")
    private String name;

    public static SidoName from(final String value) {
        return new SidoName(value);
    }

    @Builder
    public SidoName(String name) {
        this.name = name;
    }
}
