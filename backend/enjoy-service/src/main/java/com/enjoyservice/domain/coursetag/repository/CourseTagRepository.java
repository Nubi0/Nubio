package com.enjoyservice.domain.coursetag.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursetag.entity.CourseTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {

    @Query(
            "select ct.id "
                    + "from CourseTag ct "
                    + "join fetch Tag t on ct.tag = t "
                    + "where t.name.value IN :tagNames "
    )
    List<Long> findAllByCourseTags(@Param("tagNames") List<String> tagNames);


}
