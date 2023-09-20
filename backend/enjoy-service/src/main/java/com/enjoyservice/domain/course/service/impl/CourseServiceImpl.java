package com.enjoyservice.domain.course.service.impl;

import com.enjoyservice.domain.course.dto.PlaceInCourseInfoDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.mapper.course.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    @Override
    public Course save(Course course) {
        Course savedCourse = courseRepository.save(course);
        log.info("코스 저장 완료(CourseServiceImpl) : courseId = {}, memberId = {}", savedCourse.getId(), savedCourse.getMemberId());
        return savedCourse;
    }

    @Override
    public List<Course> findAllByRegionFetchPlace(Region region, Pageable pageable) {
        return courseRepository.findAllByRegionFetchPlace(region, pageable);
    }

    @Override
    public Long countAllByRegion(Region region) {
        return courseRepository.countAllByRegion(region);
    }

    @Override
    public List<Tag> findTags(Course course) {
        return courseRepository.findTagsByCourse(course);
    }

    @Override
    public List<CourseLike> findCourseLikesByCourse(Course course) {
        return courseRepository.findCourseLikesByCourse(course);
    }

    @Override
    public List<Course> findCourseAndTagsByCourseId(Long courseId) {
        return courseRepository.findCourseAndTagsByCourseId(courseId);
    }

    @Override
    public List<PlaceInCourseInfoDto> findPlacesInfoInCourseByCourse(Course course) {
        List<Place> places = courseRepository.findPlacesByCourse(course);
        log.info("Place와 fetch join 된 sequence 조회 완료(CourseServiceImpl)");
        return places.stream()
                .map(CourseMapper::toPlaceInfoInCourseDto)
                .toList();
    }
}
