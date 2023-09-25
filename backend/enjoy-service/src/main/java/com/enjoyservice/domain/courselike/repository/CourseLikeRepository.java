package com.enjoyservice.domain.courselike.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courselike.entity.type.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseLikeRepository extends JpaRepository<CourseLike, Long> {

    List<CourseLike> findAllByCourseAndActive(Course course, Active active);


}
