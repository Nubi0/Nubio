package com.enjoyservice.domain.placelike.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placelike.entity.type.Active;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "place_like")
@SQLDelete(sql = "UPDATE place_like SET active = false WHERE id = ?")
@Where(clause = "active = true")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Active active = Active.from(true);

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Builder
    public PlaceLike(String memberId, Place place) {
        this.memberId = memberId;
        this.place = place;
    }
}
