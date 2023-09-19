package com.enjoyservice.domain.course.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c, cps, p " +
            "from Course c " +
            "left join fetch CoursePlaceSequence cps on c = cps.course " +
            "join fetch Place p on cps.place = p " +
            "where c.region = :region")
    List<Course> findAllByRegionFetchPlace(@Param("region") Region region, Pageable pageable);

    Long countAllByRegion(Region region);
}
