package com.enjoyservice.domain.placereviewimage.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.placereviewimage.entity.type.Name;
import com.enjoyservice.domain.placereviewimage.entity.type.Url;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "place_img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceReviewImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Url url;
}
