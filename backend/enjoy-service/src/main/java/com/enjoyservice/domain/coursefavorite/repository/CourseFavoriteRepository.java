package com.enjoyservice.domain.coursefavorite.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursefavorite.entity.CourseFavorite;
import com.enjoyservice.domain.coursefavorite.entity.type.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseFavoriteRepository extends JpaRepository<CourseFavorite, Long> {

    boolean existsByCourseAndMemberIdAndActive(Course course, String memberId, Active active);

    Optional<CourseFavorite> findByMemberIdAndCourse(String memberId, Course course);
}
