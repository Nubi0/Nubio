package com.enjoyservice.domain.course.service;

import com.enjoyservice.domain.course.dto.CourseDto;
import com.enjoyservice.domain.course.dto.PlaceInCourseInfoDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.tag.entity.Tag;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    Course save(Course course);
    Course findById(Long courseId);
    List<Course> findAllByRegionFetchPlace(Region region, Pageable pageable);
    Long countAllByRegion(Region region);
    List<Tag> findTags(Course course);
    List<CourseLike> findCourseLikesByCourse(Course course);
    List<Course> findCourseAndTagsByCourseId(Long courseId);
    List<PlaceInCourseInfoDto> findPlacesInfoInCourseByCourse(Course course);
    boolean changeCourseLikeState(Long courseId, String memberId);
    List<CourseDto> findAllByRegionToModel(Region region);
}
