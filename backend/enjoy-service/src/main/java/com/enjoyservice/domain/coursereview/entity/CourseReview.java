package com.enjoyservice.domain.coursereview.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursereview.entity.type.Active;
import com.enjoyservice.domain.coursereview.entity.type.Content;
import com.enjoyservice.domain.coursereview.entity.type.Point;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "course_review")
@SQLDelete(sql = "UPDATE course_review SET active = false WHERE id = ?")
@Where(clause = "active = true")
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
    private Active active = Active.from(true);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Builder
    public CourseReview(Content content, Point point, Course course, String memberId) {
        this.content = content;
        this.point = point;
        this.course = course;
        this.memberId = memberId;
    }
}
