package com.enjoyservice.domain.place.entity;

import com.enjoyservice.domain.place.entity.type.KakaoId;
import com.enjoyservice.domain.place.entity.type.Name;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private KakaoId kakaoId;

    @Embedded
    private Name name;
}
