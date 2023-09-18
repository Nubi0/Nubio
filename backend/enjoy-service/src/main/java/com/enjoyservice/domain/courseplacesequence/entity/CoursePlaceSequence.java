package com.enjoyservice.domain.courseplacesequence.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.courseplacesequence.entity.type.SequenceNumber;
import com.enjoyservice.domain.place.entity.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_place_sequence")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursePlaceSequence extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SequenceNumber sequenceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Builder
    public CoursePlaceSequence(SequenceNumber sequenceNumber, Course course, Place place) {
        this.sequenceNumber = sequenceNumber;
        this.course = course;
        this.place = place;
    }

    public static CoursePlaceSequence from(int sequenceNumber, Course course, Place place) {
        return CoursePlaceSequence.builder()
                .sequenceNumber(SequenceNumber.from(sequenceNumber))
                .course(course)
                .place(place)
                .build();
    }
}
