package com.enjoyservice.api.course.service.impl;

import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.service.CourseApiService;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.courseplacesequence.service.CoursePlaceSequenceService;
import com.enjoyservice.domain.coursetag.entity.CourseTag;
import com.enjoyservice.domain.coursetag.service.CourseTagService;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.entity.type.Name;
import com.enjoyservice.domain.tag.service.TagService;
import com.enjoyservice.mapper.course.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseApiServiceImpl implements CourseApiService {

    private final CourseService courseService;
    private final PlaceService placeService;
    private final CoursePlaceSequenceService coursePlaceSequenceService;
    private final TagService tagService;
    private final CourseTagService courseTagService;

//    @Transactional
    @Override
    public void createCourse(final CourseCreateReq request, final String memberId) {
        // 코스 저장
        Course course = CourseMapper.courseCreateReqToCourse(request, memberId);
        Course savedCourse = courseService.save(course);
        log.info("course 저장 완료(CourseApiServiceImpl) : courseId = {}, memberId = {}", savedCourse.getId(), savedCourse.getMemberId());

        // 코스에 속한 장소 순서 저장
        List<Long> placeIds = collectPlaceIds(request.getPlaceInfos());
        List<Place> places = placeService.findAllById(placeIds);
        log.info("place 목록 조회(CourseApiServiceImpl)");

        Map<Long, Integer> placeSequence = mappingSequence(request.getPlaceInfos());
        List<CoursePlaceSequence> coursePlaceSequences = collectCoursePlaceSequences(course, places, placeSequence);
        coursePlaceSequenceService.saveAll(coursePlaceSequences);
        log.info("CoursePlaceSequence 목록 저장(CourseApiServiceImpl)");

        // 코스 태그 저장
        // 태그 잇는지 확인
        linkCourseTag(request, course);
        log.info("Course에 Tag 연결(CourseApiServiceImpl)");
    }

    private void linkCourseTag(CourseCreateReq request, Course course) {
        for(CourseCreateReq.TagInfo tagInfo : request.getCourseTags()) {
            Optional<Tag> opTag = tagService.findByName(Name.from(tagInfo.getValue()));
            // 있는건 바로 연결
            if(opTag.isPresent()) {
                linkExistedTag(course, opTag.get());
            } else { // 없는건 새로 저장
                linkNewTag(course, tagInfo.getValue());
            }
        }
    }

    private void linkNewTag(Course course, String tagName) {
        Tag tag = Tag.from(tagName);
        Tag savedTag = tagService.save(tag);
        linkExistedTag(course, savedTag);
    }

    private void linkExistedTag(Course course, Tag tag) {
        CourseTag courseTag = CourseTag.from(course, tag);
        courseTagService.save(courseTag);
    }

    private Map<Long, Integer> mappingSequence(List<CourseCreateReq.PlaceInfo> placeInfos) {
        return placeInfos.stream()
                .collect(Collectors.toMap(CourseCreateReq.PlaceInfo::getPlaceId,
                                            CourseCreateReq.PlaceInfo::getSequence,
                                            (oldValue, newValue) -> newValue));
    }

    private List<Long> collectPlaceIds(List<CourseCreateReq.PlaceInfo> placeInfos) {
        return placeInfos.stream()
                .map(CourseCreateReq.PlaceInfo::getPlaceId)
                .toList();
    }

    private List<CoursePlaceSequence> collectCoursePlaceSequences(Course course, List<Place> places, Map<Long, Integer> placeSequence) {
        return places.stream()
                .map(place -> CoursePlaceSequence.from(placeSequence.get(place.getId()), course, place))
                .collect(Collectors.toList());
    }

}