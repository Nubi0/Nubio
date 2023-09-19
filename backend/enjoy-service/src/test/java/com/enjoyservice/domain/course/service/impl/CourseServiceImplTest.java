package com.enjoyservice.domain.course.service.impl;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.course.service.CourseService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager em;

    private Course generateCourse(int index, Region region) {
        return Course.builder()
                .title(Title.from("courseTitle" + index))
                .content(Content.from("courseContent" + index))
                .region(region)
                .publicFlag(PublicFlag.from(true))
                .memberId("memberId" + index)
                .build();
    }

    @DisplayName("Region으로 Course 목록 조회")
    @Test
    void findAllByRegion() {
        // given
        int courseSize = 3;
        List<Course> courses = new ArrayList<>();
        for(int i = 1; i <= courseSize; i++) {
            Course course = generateCourse(i, Region.DAEGU);
            courses.add(course);
        }
        for(int i = courseSize + 1; i <= courseSize + 3; i++) {
            Course course = generateCourse(i, Region.BUSAN);
            courses.add(course);
        }
        List<Course> savedCourses = courseRepository.saveAllAndFlush(courses);
        em.clear();
        // when
        List<Course> result = courseService.findAllByRegion(Region.from("DAEGU"));
        // then
        assertThat(result).hasSize(courseSize)
                .extracting("title.value", "content.value", "region")
                .containsExactlyInAnyOrder(
                        tuple("courseTitle1", "courseContent1", Region.DAEGU),
                        tuple("courseTitle2", "courseContent2", Region.DAEGU),
                        tuple("courseTitle3", "courseContent3", Region.DAEGU)
                );
    }
}