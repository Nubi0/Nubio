package com.enjoyservice.domain.place.entity;

import com.enjoyservice.domain.common.BaseTimeEntity;
import com.enjoyservice.domain.place.entity.type.Category;
import com.enjoyservice.domain.place.entity.type.KakaoId;
import com.enjoyservice.domain.place.entity.type.Name;
import com.enjoyservice.domain.place.entity.type.Phone;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
