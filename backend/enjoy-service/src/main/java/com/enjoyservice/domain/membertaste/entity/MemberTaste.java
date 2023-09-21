package com.enjoyservice.domain.membertaste.entity;

import com.enjoyservice.domain.taste.entity.Taste;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_taste")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTaste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taste_id")
    private Taste taste;

    @Builder
    public MemberTaste(String memberId, Taste taste) {
        this.memberId = memberId;
        this.taste = taste;
    }
}
