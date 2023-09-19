package com.enjoyservice.domain.course.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c, cps, p, pi " +
            "from Course c " +
            "left join fetch CoursePlaceSequence cps on c = cps.course " +
            "join fetch Place p on cps.place = p " +
            "left join fetch PlaceImage pi on pi.place = p " +
            "where c.region = :region")
    List<Course> findAllByRegionFetchPlace(@Param("region") Region region, Pageable pageable);

    Long countAllByRegion(Region region);

    @Query("select t " +
            "from Course c " +
            "left join fetch CourseTag ct on c = ct.course " +
            "join fetch Tag t on ct.tag = t " +
            "where c = :course")
    List<Tag> findTagsByCourse(@Param("course") Course course);

    @Query("select cl, c from Course c " +
            "left join fetch CourseLike cl on c = cl.course " +
            "where c = :course")
    List<CourseLike> findCourseLikesByCourse(@Param("course") Course course);

    @Query("select distinct c, t " +
            "from Course c " +
            "left join fetch CourseTag ct on c = ct.course " +
            "join fetch Tag t on ct.tag = t " +
            "where c.id = :courseId")
    List<Course> findCourseAndTagsByCourseId(@Param("courseId") Long courseId);
}
