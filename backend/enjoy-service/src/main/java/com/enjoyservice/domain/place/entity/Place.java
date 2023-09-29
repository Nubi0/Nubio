package com.enjoyservice.domain.place.entity;

import com.enjoyservice.domain.common.BaseTimeEntity;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.placeimage.entity.PlaceImage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "place")
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

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PlaceImage> images = new HashSet<>();

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CoursePlaceSequence> sequences = new ArrayList<>();

    @Builder
    public Place(KakaoId kakaoId, Name name, Category category, Phone phone,
                 Url url, Address address, Position position, Active active) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.category = category;
        this.phone = phone;
        this.url = url;
        this.address = address;
        this.position = position;
        this.active = active;
    }

    public void addImage(PlaceImage placeImage) {
        images.add(placeImage);
        placeImage.updatePlace(this);
    }
}
