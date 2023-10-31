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
public class UmdName {

    @Column(name = "umd_name")
    private String name;

    @Builder
    public UmdName(String name) {
        this.name = name;
    }

    public static UmdName from(final String value) {
        return new UmdName(value);
    }

}
