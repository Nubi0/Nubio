package com.enjoyservice.domain.placeimage.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placeimage.entity.type.Name;
import com.enjoyservice.domain.placeimage.entity.type.Url;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "place_img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Url url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
}
