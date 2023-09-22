package com.enjoyservice.domain.course.service.impl;

import com.enjoyservice.domain.course.dto.PlaceInCourseInfoDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.exception.CourseNotFoundException;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courselike.repository.CourseLikeRepository;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.BusinessException;
import com.enjoyservice.mapper.course.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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
    public Page<Course> findAllByCourseTags(List<Long> courseTagIds, Pageable pageable) {
        return courseRepository.findAllByCourseTags(courseTagIds, courseTagIds.size(), pageable);
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
}
