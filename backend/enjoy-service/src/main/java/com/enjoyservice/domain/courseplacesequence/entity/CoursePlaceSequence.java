package com.enjoyservice.domain.courseplacesequence.entity;

import com.enjoyservice.domain.common.BaseEntity;
import com.enjoyservice.domain.courseplacesequence.entity.type.SequenceNumber;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_place_sequence")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursePlaceSequence extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SequenceNumber sequenceNumber;


}
