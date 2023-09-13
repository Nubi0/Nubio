package com.enjoyservice.domain.place.entity;

import com.enjoyservice.domain.common.BaseTimeEntity;
import com.enjoyservice.domain.place.entity.type.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private KakaoId kakaoId;

    @Embedded
    private Name name;

    @Embedded
    private Category category;

    @Embedded
    private Phone phone;

    @Embedded
    private Url url;

    @Embedded
    private Address address;

    @Embedded
    private Position position;

    @Embedded
    private Active active;

    @Builder
    public Place(Long id, KakaoId kakaoId, Name name, Category category, Phone phone,
                 Url url, Address address, Position position, Active active) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.name = name;
        this.category = category;
        this.phone = phone;
        this.url = url;
        this.address = address;
        this.position = position;
        this.active = active;
    }
}