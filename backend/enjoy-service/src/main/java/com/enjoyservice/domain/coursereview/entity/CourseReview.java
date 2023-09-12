package com.enjoyservice.domain.coursereview.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursereview.entity.type.Active;
import com.enjoyservice.domain.coursereview.entity.type.Content;
import com.enjoyservice.domain.coursereview.entity.type.Point;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CourseReview extends BaseEntity {

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
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "member_id", nullable = false)
    private String memberId;
}
