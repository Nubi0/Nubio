package com.enjoyservice.domain.courselike.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.placelike.entity.type.Active;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "course_like")
@SQLDelete(sql = "UPDATE course_like SET active = false WHERE id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Active active = Active.from(true);

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

    public boolean changeActiveValue() {
        return this.getActive().changeValue();
    }
}
