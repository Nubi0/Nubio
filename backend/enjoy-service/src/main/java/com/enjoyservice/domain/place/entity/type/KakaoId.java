package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoId {

    @Column(name = "kakao_id")
    private String value;

    private KakaoId(final String value) {
        this.value = value;
    }

    public static KakaoId from(final String value) {
        return new KakaoId(value);
    }
}
