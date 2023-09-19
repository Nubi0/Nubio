package com.enjoyservice.domain.courselike.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.course.entity.Course;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "member_id")
    private String memberId;

    @Builder
    public CourseLike(Course course, String memberId) {
        this.course = course;
        this.memberId = memberId;
    }
}
