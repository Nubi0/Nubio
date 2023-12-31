package com.enjoyservice.domain.course.service.impl;

import com.enjoyservice.domain.course.dto.CourseDto;
import com.enjoyservice.domain.course.dto.PlaceInCourseInfoDto;
import com.enjoyservice.domain.course.dto.RecommendationPlaceDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.exception.CourseNotFoundException;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courselike.repository.CourseLikeRepository;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.mapper.course.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseLikeRepository courseLikeRepository;

    @Transactional
    @Override
    public Course save(Course course) {
        Course savedCourse = courseRepository.save(course);
        log.info("코스 저장 완료(CourseServiceImpl) : courseId = {}, memberId = {}", savedCourse.getId(), savedCourse.getMemberId());
        return savedCourse;
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(ErrorCode.COURSE_NOT_FOUND));
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
    public Course findCourseAndTagsByCourseId(Long courseId) {
        List<Course> result = courseRepository.findCourseAndTagsByCourseId(courseId);
        return result.get(0);
    }

    @Override
    public List<PlaceInCourseInfoDto> findPlacesInfoInCourseByCourse(Course course) {
        List<Course> courses = courseRepository.findPlacesByCourse(course);
        List<Place> places = courses.get(0).getCoursePlaceSequences().stream()
                        .map(CoursePlaceSequence::getPlace)
                        .toList();
        log.info("Place와 fetch join 된 sequence 조회 완료(CourseServiceImpl)");
        return places.stream()
                .map(CourseMapper::toPlaceInfoInCourseDto)
                .toList();
    }

    @Override
    public boolean changeCourseLikeState(Long courseId, String memberId) {
        Optional<CourseLike> opCourseLike = courseRepository.findCourseLikesByCourseId(courseId, memberId);
        // 이미 해당 코스를 좋아요 한 기록이 있으면
        if(opCourseLike.isPresent()) {
            CourseLike courseLike = opCourseLike.get();
            // 상태 변화
            return courseLike.changeActiveValue();
        }
        // 아직 해당 장소를 한 번도 좋아요 한 적이 없으면 새로 만들어서 결과 반환
        return createCourseLike(memberId, courseId);
    }

    @Override
    public List<CourseDto> findAllByRegionToModel(Region region) {
        return courseRepository.findCourseForModel(region);
    }
  
    @Override
    public Page<Course> findAllByCourseTags(List<Long> courseTagIds, Pageable pageable) {
        return courseRepository.findAllByCourseTags(courseTagIds, courseTagIds.size(), pageable);
    }

    @Override
    public Page<Course> findAllByCourseTagsAndRegion(List<Long> courseTagIds, Region region, Pageable pageable) {
        return courseRepository.findAllByCourseTagsAndRegion(courseTagIds, courseTagIds.size(), region, pageable);
    }

    @Override
    public List<RecommendationPlaceDto> findPlaceByCourse(Course course) {
        List<Place> placesAndImageByCourse = courseRepository.findPlacesAndImageByCourse(course);
        log.info("places length = {} " , placesAndImageByCourse.size());
        return placesAndImageByCourse.stream()
                .map(RecommendationPlaceDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Course> findFavoriteCourseByMember(String memberId, Pageable pageable) {
        return courseRepository.findFavoriteCourseByMember(memberId, pageable);
    }

    private boolean createCourseLike(String memberId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(ErrorCode.COURSE_NOT_FOUND));
        CourseLike courseLike = CourseLike.builder()
                .memberId(memberId)
                .course(course)
                .build();
        // 새로 만들어서 결과 반환
        courseLikeRepository.save(courseLike);
        return courseLike.getActive().isValue();
    }

    @Override
    public Page<Course> findMyCourses(String memberId, Pageable pageable) {
        return courseRepository.findMyCourses(memberId, pageable);
    }
}
