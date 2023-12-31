package com.enjoyservice.domain.course.service;

import com.enjoyservice.domain.course.dto.CourseDto;
import com.enjoyservice.domain.course.dto.PlaceInCourseInfoDto;
import com.enjoyservice.domain.course.dto.RecommendationPlaceDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.tag.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseService {

    Course save(Course course);

    Course findById(Long courseId);

    List<Course> findAllByRegionFetchPlace(Region region, Pageable pageable);

    Long countAllByRegion(Region region);

    List<Tag> findTags(Course course);

    List<CourseLike> findCourseLikesByCourse(Course course);

    Course findCourseAndTagsByCourseId(Long courseId);

    List<PlaceInCourseInfoDto> findPlacesInfoInCourseByCourse(Course course);

    boolean changeCourseLikeState(Long courseId, String memberId);

    List<CourseDto> findAllByRegionToModel(Region region);

    Page<Course> findAllByCourseTags(List<Long> courseTagIds, Pageable pageable);

    Page<Course> findAllByCourseTagsAndRegion(List<Long> courseTagIds, Region region, Pageable pageable);

    List<RecommendationPlaceDto> findPlaceByCourse(Course course);

    Page<Course> findMyCourses(String memberId, Pageable pageable);

    Page<Course> findFavoriteCourseByMember(String memberId, Pageable pageable);

}
