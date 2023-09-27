package com.enjoyservice.domain.place.entity.type;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoId {

    @Column(name = "kakao_id")
    private Long value;

    private KakaoId(final long value) {
        this.value = value;
    }

    public static KakaoId from(final long value) {
        return new KakaoId(value);
    }
}
