package com.enjoyservice.domain.placereview.entity;

import com.enjoyservice.domain.common.BaseEntity;
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
}
