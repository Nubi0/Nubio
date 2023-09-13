package com.enjoyservice.domain.placereview.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placereview.entity.type.Active;
import com.enjoyservice.domain.placereview.entity.type.Content;
import com.enjoyservice.domain.placereview.entity.type.Point;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "place_review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PlaceReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Content content;

    @Embedded
    private Point point;

    @Embedded
    private Active active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(name = "member_id", nullable = false)
    private String memberId;
}
