package com.enjoyservice.domain.coursefavorite.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursefavorite.entity.type.Active;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_favorite")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseFavorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "member_id")
    private String memberId;

    @Embedded
    private Active active = Active.from(true);

    @Builder
    public CourseFavorite(Course course, String memberId) {
        this.course = course;
        this.memberId = memberId;
    }

    public boolean changeActiveValue() {
        return this.getActive().changeValue();
    }
}
