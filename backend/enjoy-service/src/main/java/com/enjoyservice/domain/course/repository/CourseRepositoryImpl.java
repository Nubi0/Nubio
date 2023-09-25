package com.enjoyservice.domain.course.repository;

import com.enjoyservice.domain.course.dto.CourseDto;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.enjoyservice.domain.course.entity.QCourse.course;
import static com.enjoyservice.domain.courseplacesequence.entity.QCoursePlaceSequence.coursePlaceSequence;
import static com.enjoyservice.domain.place.entity.QPlace.place;

@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CourseDto> findCourseForModel(Region region) {
        return jpaQueryFactory
                .select(Projections.constructor(CourseDto.class,
                        course.id, course.memberId,
                        Expressions.stringTemplate("group_concat({0})",place.category.detail).as("category_detail"),
                        Expressions.stringTemplate("group_concat({0})",place.category.groupName).as("category_name"))
                )
                .from(course)
                .leftJoin(coursePlaceSequence).on(course.id.eq(coursePlaceSequence.course.id))
                .innerJoin(place).on(coursePlaceSequence.place.id.eq(place.id))
                .where(course.region.eq(region))
                .groupBy(course.id, course.memberId)
                .fetch();
    }
}
