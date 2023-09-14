package com.enjoyservice.domain.placefavorite.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.place.entity.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceFavorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Builder
    public PlaceFavorite(String memberId, Place place) {
        this.memberId = memberId;
        this.place = place;
    }
}
