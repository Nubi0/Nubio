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
public class RiName {

    @Column(name = "ri_name")
    private String name;

    @Builder
    public RiName(String name) {
        this.name = name;
    }

}
