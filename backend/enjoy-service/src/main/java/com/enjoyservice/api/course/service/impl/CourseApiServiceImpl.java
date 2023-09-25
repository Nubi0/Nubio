package com.enjoyservice.api.course.service.impl;

import com.enjoyservice.api.course.dto.*;
import com.enjoyservice.api.course.service.CourseApiService;
import com.enjoyservice.domain.course.dto.PlaceInCourseInfoDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.coursefavorite.service.CourseFavoriteService;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.courseplacesequence.service.CoursePlaceSequenceService;
import com.enjoyservice.domain.coursetag.entity.CourseTag;
import com.enjoyservice.domain.coursetag.service.CourseTagService;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.type.KakaoId;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.entity.type.Name;
import com.enjoyservice.domain.tag.service.TagService;
import com.enjoyservice.mapper.course.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final CourseFavoriteService courseFavoriteService;

    @Transactional
    @Override
    public CourseCreateRes createCourse(final CourseCreateReq request, final String memberId) {
        // 코스 저장
        Course course = CourseMapper.courseCreateReqToCourse(request, memberId);
        Course savedCourse = courseService.save(course);
        log.info("course 저장 완료(CourseApiServiceImpl) : courseId = {}, memberId = {}", savedCourse.getId(), savedCourse.getMemberId());

        // 코스에 속한 장소 순서 저장
        List<Integer> placeKakaoIds = collectKakaoIds(request.getPlaceInfos());
        log.info("placeKakaoIds : {}", placeKakaoIds);
        List<KakaoId> kakaoIds = placeKakaoIds.stream()
                .map(id -> KakaoId.from(id.intValue()))
                .toList();
        log.info("kakaoIds : {}", kakaoIds.stream().map(id -> id.getValue()).toList());
        List<Place> places = placeService.findAllByKakaoId(kakaoIds);
        log.info("place 목록 조회 완료(CourseApiServiceImpl), places : {}", places);

        Map<Integer, Integer> placeSequence = mappingSequence(request.getPlaceInfos());
        log.info("placeSequence 맵 : {}", placeSequence);
        List<CoursePlaceSequence> coursePlaceSequences = collectCoursePlaceSequences(course, places, placeSequence);
        log.info("coursePlaceSequences 리스트 : {}, 크기: {}", coursePlaceSequences, coursePlaceSequences.size());
        coursePlaceSequenceService.saveAll(coursePlaceSequences);
        log.info("CoursePlaceSequence 목록 저장 완료(CourseApiServiceImpl)");

        // 코스 태그 저장
        // 태그 잇는지 확인
        linkCourseTag(request, course);
        log.info("Course에 Tag 연결 완료(CourseApiServiceImpl)");

        return new CourseCreateRes(savedCourse.getId());
    }

    @Override
    public CourseListRes getCourseList(String region, String memberId, Pageable pageable) {
        // 코스 - 장소 가져오기
        List<CourseListRes.CourseInfo> courseInfos = new ArrayList<>();
        List<Course> courses = courseService.findAllByRegionFetchPlace(Region.from(region), pageable);
        log.info("Region으로 Course 조회할 때 fetch로 Place, PlaceImage 모두 조회 완료(CourseApiServiceImpl) : region = {}", region);
        for(Course course : courses) {
            // 코스 태그
            List<Tag> tags = courseService.findTags(course);
            log.info("Course에 연관된 Tag 목록 조회 완료(CourseApiServiceImpl)");
            // 코스 즐겨찾기
            boolean favoriteFlag = courseFavoriteService.existsByCourseAndMemberId(course, memberId);
            log.info("Course를 즐겨찾기 했는지 확인 완료(CourseApiServiceImpl)");
            // 코스 좋아요 - 좋아요 수, 내가 좋아요 했는지
            List<CourseLike> courseLikes = courseService.findCourseLikesByCourse(course);
            int likeCount = courseLikes.size();
            boolean likeFlag = isMemberLikeCourse(memberId, courseLikes);
            log.info("Course를 좋아요 했는지 확인, 좋아요 수 조회 완료(CourseApiServiceImpl)");
            CourseListRes.CourseInfo courseInfo = CourseMapper.convertToCourseInfo(course, tags, favoriteFlag, likeCount, likeFlag);
            courseInfos.add(courseInfo);
        }
        // 페이징 meta
        Long totalElements = courseService.countAllByRegion(Region.from(region));
        log.info("Course 전체 수 조회 완료(CourseApiServiceImpl)");

        return CourseMapper.courseToCourseListRes(courseInfos, totalElements, pageable);
    }

    @Override
    public CourseDetailRes getCourseDetail(Long courseId, String memberId) {
        // TODO: CourseService에서 courses.get(0) 한 결과를 반환하는게 더 좋을지도??
//        Course course = courseService.findCourseAndTagsByCourseId(courseId);
//        Course course = courses.get(0);
        Course course = courseService.findById(courseId);
        List<Tag> tags = course.getCourseTags().stream()
                .map(CourseTag::getTag)
                .toList();
        log.info("Course, tags 조회 완료(CourseApiServiceImpl)");
        // 코스 즐겨찾기 했는지
        boolean favoriteFlag = courseFavoriteService.existsByCourseAndMemberId(course, memberId);
        log.info("Course를 즐겨찾기 했는지 확인 완료(CourseApiServiceImpl)");
        // 코스 좋아요 - 좋아요 수, 내가 좋아요 했는지
        List<CourseLike> courseLikes = courseService.findCourseLikesByCourse(course);
        int likeCount = courseLikes.size();
        boolean likeFlag = isMemberLikeCourse(memberId, courseLikes);
        log.info("Course를 좋아요 했는지 확인, 좋아요 수 조회 완료(CourseApiServiceImpl)");
        // Course에 속한 Place 정보들 조회
        List<PlaceInCourseInfoDto> placeInfos = courseService.findPlacesInfoInCourseByCourse(course);
        log.info("Course에 속한 Place 정보들 조회 완료(CourseApiServiceImpl)");

        return CourseMapper.toCourseDetailRes(course, tags, favoriteFlag, likeCount, likeFlag, placeInfos);
    }

    @Override
    public CourseListRes findAllByCourseTags(CourseTagListReq courseTagListReq, String memberId, Pageable pageable) {
        // 코스 - 장소 가져오기
        List<CourseListRes.CourseInfo> courseInfos = new ArrayList<>();
        List<Long> allByCourseTags = courseTagService.findAllByCourseTags(courseTagListReq.getCourse_tags());
        Page<Course>  coursePage= courseService.findAllByCourseTags(allByCourseTags, pageable);
        List<Course> courses = coursePage.getContent();
        for(Course course : courses) {
            // 코스 태그
            List<Tag> tags = courseService.findTags(course);
            log.info("Course에 연관된 Tag 목록 조회 완료(CourseApiServiceImpl)");
            // 코스 즐겨찾기
            boolean favoriteFlag = courseFavoriteService.existsByCourseAndMemberId(course, memberId);
            log.info("Course를 즐겨찾기 했는지 확인 완료(CourseApiServiceImpl)");
            // 코스 좋아요 - 좋아요 수, 내가 좋아요 했는지
            List<CourseLike> courseLikes = courseService.findCourseLikesByCourse(course);
            int likeCount = courseLikes.size();
            boolean likeFlag = isMemberLikeCourse(memberId, courseLikes);
            log.info("Course를 좋아요 했는지 확인, 좋아요 수 조회 완료(CourseApiServiceImpl)");
            CourseListRes.CourseInfo courseInfo = CourseMapper.convertToCourseInfo(course, tags, favoriteFlag, likeCount, likeFlag);
            courseInfos.add(courseInfo);
        }
        // 페이징 meta

        return CourseMapper.courseToCourseListRes(courseInfos,coursePage);

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

    private Map<Integer, Integer> mappingSequence(List<CourseCreateReq.PlaceInfo> placeInfos) {
        return placeInfos.stream()
                .collect(Collectors.toMap(CourseCreateReq.PlaceInfo::getKakaoId,
                                            CourseCreateReq.PlaceInfo::getSequence,
                                            (oldValue, newValue) -> newValue));
    }

    private List<Integer> collectKakaoIds(List<CourseCreateReq.PlaceInfo> placeInfos) {
        return placeInfos.stream()
                .map(CourseCreateReq.PlaceInfo::getKakaoId)
                .toList();
    }

    private List<CoursePlaceSequence> collectCoursePlaceSequences(Course course, List<Place> places, Map<Integer, Integer> placeSequence) {
        List<CoursePlaceSequence> sequences = new ArrayList<>();
        for(Place place : places) {
            CoursePlaceSequence seq = CoursePlaceSequence.from(placeSequence.get(place.getKakaoId().getValue()), course, place);
            sequences.add(seq);
        }
        return sequences;

//        return places.stream()
//                .map(place -> CoursePlaceSequence.from(placeSequence.get(place.getId()), course, place))
//                .collect(Collectors.toList());
    }

    private boolean isMemberLikeCourse(String memberId, List<CourseLike> likes) {
        return likes.stream()
                .filter(Objects::nonNull)
                .anyMatch(courseLike -> courseLike.getMemberId().equals(memberId));
    }

}
