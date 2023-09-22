package com.enjoyservice.domain.course.service.impl;

import com.enjoyservice.domain.course.dto.PlaceInCourseInfoDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courselike.repository.CourseLikeRepository;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.courseplacesequence.entity.type.SequenceNumber;
import com.enjoyservice.domain.courseplacesequence.repository.CoursePlaceSequenceRepository;
import com.enjoyservice.domain.coursetag.entity.CourseTag;
import com.enjoyservice.domain.coursetag.repository.CourseTagRepository;
import com.enjoyservice.domain.coursetag.service.CourseTagService;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.repository.TagRepository;
import com.enjoyservice.domain.tag.service.TagService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CoursePlaceSequenceRepository coursePlaceSequenceRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private CourseTagRepository courseTagRepository;
    @Autowired
    private CourseLikeRepository courseLikeRepository;

    @Autowired
    private EntityManager em;

    private List<Course> savedBeforeCourses;
    private List<Place> savedBeforePlaces;
    private List<CoursePlaceSequence> savedBeforeCoursePlaceSequences;

    @BeforeEach
    void setUp() {
        // 코스 저장
        int courseSize = 2;
        List<Course> courses = new ArrayList<>();
        for (int i = 1; i <= courseSize; i++) {
            Course course = generateCourse(i, Region.DAEGU);
            courses.add(course);
        }
        for (int i = courseSize + 1; i <= courseSize + 3; i++) {
            Course course = generateCourse(i, Region.BUSAN);
            courses.add(course);
        }
        savedBeforeCourses = courseRepository.saveAllAndFlush(courses);
        em.clear();

        // 장소 저장
        int placeSize = 5;
        List<Place> places = new ArrayList<>();
        for (int i = 1; i <= placeSize; i++) {
            Place place = generatePlace(i, GroupCode.CE7, GroupName.카페);
            places.add(place);
        }
        savedBeforePlaces = placeRepository.saveAllAndFlush(places);
        em.clear();

        // CoursePlaceSequence 저장
        // 1번 코스에 장소 3개 추가
        List<CoursePlaceSequence> coursePlaceSequences = new ArrayList<>();
        for (int seq = 1; seq <= 3; seq++) {
            CoursePlaceSequence coursePlaceSequence = CoursePlaceSequence.builder()
                    .place(savedBeforePlaces.get(seq - 1))
                    .course(savedBeforeCourses.get(0))
                    .sequenceNumber(SequenceNumber.from(seq))
                    .build();
            coursePlaceSequences.add(coursePlaceSequence);
        }
        // 2번 코스에 장소 2개 추가
        for (int seq = 1; seq <= 2; seq++) {
            CoursePlaceSequence coursePlaceSequence = CoursePlaceSequence.builder()
                    .place(savedBeforePlaces.get(seq + 2))
                    .course(savedBeforeCourses.get(1))
                    .sequenceNumber(SequenceNumber.from(seq))
                    .build();
            coursePlaceSequences.add(coursePlaceSequence);
        }
        savedBeforeCoursePlaceSequences = coursePlaceSequenceRepository.saveAllAndFlush(coursePlaceSequences);
        em.clear();

        log.info("=============================setUp==================================");
    }

    private Course generateCourse(int index, Region region) {
        return Course.builder()
                .title(Title.from("courseTitle" + index))
                .content(Content.from("courseContent" + index))
                .region(region)
                .publicFlag(PublicFlag.from(true))
                .memberId("memberId" + index)
                .build();
    }

    private Place generatePlace(int index, GroupCode groupCode, GroupName groupName) {
        return Place.builder()
                .kakaoId(KakaoId.from(index))
                .name(Name.from("place" + index))
                .category(Category.from(groupCode, groupName, Detail.from("category detail" + index)))
                .phone(Phone.from("010-1111-111" + index))
                .url(Url.from("abc" + index + ".nubio.com"))
                .address(Address.builder()
                        .name("주소" + index)
                        .roadName(RoadName.from("도로명주소" + index)).build()
                )
                .position(Position.builder()
                        .latitude(Latitude.from(Double.valueOf("37.263906325599" + index)))
                        .longitude(Longitude.from(Double.valueOf("127.032310032317" + index)))
                        .build())
                .active(Active.from(true))
                .build();
    }

    @DisplayName("Region으로 Course 목록 조회 : CoursePlaceSequence, Place fetch join")
    @Test
    void findAllByRegion() {
        // given
        // when
        List<Course> result = courseService.findAllByRegionFetchPlace(Region.from("DAEGU"), PageRequest.of(0, 5));
        // then
        assertThat(result).hasSize(5)
                .extracting("title.value", "content.value", "region")
                .containsExactlyInAnyOrder(
                        tuple("courseTitle1", "courseContent1", Region.DAEGU),
                        tuple("courseTitle1", "courseContent1", Region.DAEGU),
                        tuple("courseTitle1", "courseContent1", Region.DAEGU),
                        tuple("courseTitle2", "courseContent2", Region.DAEGU),
                        tuple("courseTitle2", "courseContent2", Region.DAEGU)
                );
    }

    @DisplayName("Region으로 Course 전체 개수 구하기")
    @Test
    void countAllByRegion() {
        // given
        Region region = Region.from("DAEGU");
        // when
        Long result = courseService.countAllByRegion(region);
        // then
        assertThat(result).isEqualTo(2);
    }

    @DisplayName("Course의 Tag 모두 조회")
    @Test
    void findTags() {
        // given
        Course course = savedBeforeCourses.get(0);
        int tagCount = 5;
        List<Tag> tags = new ArrayList<>();
        for (int i = 1; i <= tagCount; i++) {
            Tag tag = Tag.from("tag" + i);
            tags.add(tag);
        }
        List<Tag> savedTags = tagRepository.saveAllAndFlush(tags);
        em.clear();

        List<CourseTag> courseTags = new ArrayList<>();
        for (Tag tag : savedTags) {
            CourseTag courseTag = CourseTag.builder()
                    .course(course)
                    .tag(tag)
                    .build();
            courseTags.add(courseTag);
        }
        List<CourseTag> savedCourseTags = courseTagRepository.saveAllAndFlush(courseTags);
        em.clear();
        // when
        List<Tag> result = courseService.findTags(course);
        // then
        assertThat(result).hasSize(tagCount)
                .extracting("name.value")
                .containsExactlyInAnyOrder(
                        "tag1",
                        "tag2",
                        "tag3",
                        "tag4",
                        "tag5"
                );
    }

    @DisplayName("Course의 모든 CourseLike 조회")
    @Test
    void findCourseLikesByCourse() {
        // given
        String memberId1 = "memberId1";
        String memberId2 = "memberId2";
        String memberId3 = "memberId3";
        List<String> memberIds = List.of(memberId1, memberId2, memberId3);

        Course course = savedBeforeCourses.get(0);

        List<CourseLike> courseLikes = new ArrayList<>();
        for (String memberId : memberIds) {
            CourseLike courseLike = CourseLike.builder()
                    .course(course)
                    .memberId(memberId)
                    .build();
            courseLikes.add(courseLike);
        }
        List<CourseLike> savedCourseLikes = courseLikeRepository.saveAllAndFlush(courseLikes);
        em.clear();
        // when
        List<CourseLike> result = courseService.findCourseLikesByCourse(course);
        // then
        assertThat(result).hasSize(memberIds.size())
                .extracting("course.title.value", "memberId")
                .containsExactlyInAnyOrder(
                        tuple("courseTitle1", memberId1),
                        tuple("courseTitle1", memberId2),
                        tuple("courseTitle1", memberId3)
                );
    }

    @DisplayName("CourseTag로 연관된 Course 모두 조회하기")
    @Test
    void findAllByCourseTags() {
        // given
        Course course = savedBeforeCourses.get(0);
        int tagCount = 5;
        List<Tag> tags = new ArrayList<>();
        for (int i = 1; i <= tagCount; i++) {
            Tag tag = Tag.from("tag" + i);
            tags.add(tag);
        }
        List<Tag> savedTags = tagRepository.saveAllAndFlush(tags);
        em.clear();

        List<CourseTag> courseTags = new ArrayList<>();
        for (Tag tag : savedTags) {
            CourseTag courseTag = CourseTag.builder()
                    .course(course)
                    .tag(tag)
                    .build();
            courseTags.add(courseTag);
        }
        List<CourseTag> savedCourseTags = courseTagRepository.saveAllAndFlush(courseTags);
        em.clear();

        List<String> tagNameList = tags.stream()
                .map(tag -> tag.getName().getValue())
                .collect(Collectors.toList());

        //when
        List<Long> allByCourseTags = courseTagRepository.findAllByCourseTags(tagNameList);
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");
        Page<Course> coursePage = courseService.findAllByCourseTags(allByCourseTags, pageable);

        // then
        // 페이지당 데이터 개수
        assertThat(coursePage.getSize()).isEqualTo(pageSize);
        // 현재 페이지 번호 0부터 시작
        assertThat(coursePage.getNumber()).isEqualTo(0);
        // 다음 페이지 존재 여부
        assertThat(coursePage.hasNext()).isFalse();
        // 시작 페이지(0) 여부
        assertThat(coursePage.isFirst()).isTrue();
        assertThat(coursePage).hasSize(1);
    }

    @DisplayName("CourseTag로 연관된 Course 없는 경우 조회")
    @Test
    void findNotCourseTags() {
        // given
        Course course = savedBeforeCourses.get(0);
        int tagCount = 5;
        List<Tag> tags = new ArrayList<>();
        for (int i = 1; i <= tagCount; i++) {
            Tag tag = Tag.from("tag" + i);
            tags.add(tag);
        }
        List<Tag> savedTags = tagRepository.saveAllAndFlush(tags);
        em.clear();

        List<CourseTag> courseTags = new ArrayList<>();
        List<CourseTag> savedCourseTags = courseTagRepository.saveAllAndFlush(courseTags);
        em.clear();

        List<String> tagNameList = tags.stream()
                .map(tag -> tag.getName().getValue())
                .collect(Collectors.toList());

        //when
        List<Long> allByCourseTags = courseTagRepository.findAllByCourseTags(tagNameList);
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");
        Page<Course> coursePage = courseService.findAllByCourseTags(allByCourseTags, pageable);

        // then
        // 페이지당 데이터 개수
        assertThat(coursePage.getSize()).isEqualTo(pageSize);
        // 현재 페이지 번호 0부터 시작
        assertThat(coursePage.getNumber()).isEqualTo(0);
        // 다음 페이지 존재 여부
        assertThat(coursePage.hasNext()).isFalse();
        // 시작 페이지(0) 여부
        assertThat(coursePage.isFirst()).isTrue();
        assertThat(coursePage).hasSize(0);
    }

    @DisplayName("일부분 CourseTag로 연관된 Course 모두 조회")
    @Test
    void findSubCourseTags() {
        // given
        Course course = savedBeforeCourses.get(0);
        int tagCount = 5;
        List<Tag> tags = new ArrayList<>();
        for (int i = 1; i <= tagCount; i++) {
            Tag tag = Tag.from("tag" + i);
            tags.add(tag);
        }
        List<Tag> savedTags = tagRepository.saveAllAndFlush(tags);
        em.clear();

        List<CourseTag> courseTags = new ArrayList<>();
        for (Tag tag : savedTags) {
            CourseTag courseTag = CourseTag.builder()
                    .course(course)
                    .tag(tag)
                    .build();
            courseTags.add(courseTag);
        }
        List<CourseTag> savedCourseTags = courseTagRepository.saveAllAndFlush(courseTags);
        em.clear();

        List<String> tagNameList = tags.stream()
                .map(tag -> tag.getName().getValue())
                .collect(Collectors.toList());

        //when
        List<Long> allByCourseTags = List.of(savedCourseTags.get(0).getId());
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");
        Page<Course> coursePage = courseService.findAllByCourseTags(allByCourseTags, pageable);

        // then
        // 페이지당 데이터 개수
        assertThat(coursePage.getSize()).isEqualTo(pageSize);
        // 현재 페이지 번호 0부터 시작
        assertThat(coursePage.getNumber()).isEqualTo(0);
        // 다음 페이지 존재 여부
        assertThat(coursePage.hasNext()).isFalse();
        // 시작 페이지(0) 여부
        assertThat(coursePage.isFirst()).isTrue();
        assertThat(coursePage).hasSize(1);
    }

    @DisplayName("CourseId로 연관된 Tag 모두 조회하기")
    @Test
    void findTagsByCourseId() {
        // given
        Course course = savedBeforeCourses.get(0);
        int tagCount = 5;
        List<Tag> tags = new ArrayList<>();
        for (int i = 1; i <= tagCount; i++) {
            Tag tag = Tag.from("tag" + i);
            tags.add(tag);
        }
        List<Tag> savedTags = tagRepository.saveAllAndFlush(tags);
        em.clear();

        List<CourseTag> courseTags = new ArrayList<>();
        for (Tag tag : savedTags) {
            CourseTag courseTag = CourseTag.builder()
                    .course(course)
                    .tag(tag)
                    .build();
            courseTags.add(courseTag);
        }
        List<CourseTag> savedCourseTags = courseTagRepository.saveAllAndFlush(courseTags);
        em.clear();
        // when
        List<Course> result = courseService.findCourseAndTagsByCourseId(course.getId());
        // then
        assertThat(result.size()).isEqualTo(tagCount);
        assertThat(result.get(0).getCourseTags().get(0).getTag().getName().getValue()).isEqualTo("tag1");
        assertThat(result.get(0).getCourseTags().get(1).getTag().getName().getValue()).isEqualTo("tag2");
        assertThat(result.get(0).getCourseTags().get(2).getTag().getName().getValue()).isEqualTo("tag3");
        assertThat(result.get(0).getCourseTags().get(3).getTag().getName().getValue()).isEqualTo("tag4");
        assertThat(result.get(0).getCourseTags().get(4).getTag().getName().getValue()).isEqualTo("tag5");
    }

    @DisplayName("Course안에 속한 Place 목록 정보 조회(Place 순서 포함)")
    @Test
    void findPlacesInfoInCourseByCourse() {
        // given
        Course course = savedBeforeCourses.get(0);
        Place place1 = savedBeforePlaces.get(0);
        Place place2 = savedBeforePlaces.get(1);
        Place place3 = savedBeforePlaces.get(2);
        CoursePlaceSequence coursePlaceSequence1 = savedBeforeCoursePlaceSequences.get(0);
        CoursePlaceSequence coursePlaceSequence2 = savedBeforeCoursePlaceSequences.get(1);
        CoursePlaceSequence coursePlaceSequence3 = savedBeforeCoursePlaceSequences.get(2);
        // when
        List<PlaceInCourseInfoDto> result = courseService.findPlacesInfoInCourseByCourse(course);
        // then
        assertThat(result).hasSize(3)
                .extracting("id", "sequence")
                .containsExactlyInAnyOrder(
                        tuple(place1.getId(), coursePlaceSequence1.getSequenceNumber().getValue()),
                        tuple(place2.getId(), coursePlaceSequence2.getSequenceNumber().getValue()),
                        tuple(place3.getId(), coursePlaceSequence3.getSequenceNumber().getValue())
                );

    }

    @DisplayName("코스를 좋아요 한 적 없으면 새로 생성함")
    @Test
    void changeCourseLikeState1() {
        // given
        String memberId = "memberId";
        Course course = savedBeforeCourses.get(0);
        Optional<CourseLike> beforeOpCourseLike = courseRepository.findCourseLikesByCourseId(course.getId(), memberId);
        em.flush();
        em.clear();
        // when
        boolean result = courseService.changeCourseLikeState(course.getId(), memberId);
        em.flush();
        em.clear();
        Optional<CourseLike> resultOpCourseLike = courseRepository.findCourseLikesByCourseId(course.getId(), memberId);
        em.flush();
        em.clear();
        // then
        assertThat(beforeOpCourseLike).isEmpty();
        assertTrue(result);
        assertThat(resultOpCourseLike).isNotEmpty();
    }

    @DisplayName("코스가 이미 좋아요 되어 있으면 좋아요 취소")
    @Test
    void changeCourseLikeState2() {
        // given
        String memberId = "memberId";
        Course course = savedBeforeCourses.get(0);
        CourseLike courseLike = CourseLike.builder()
                .memberId(memberId)
                .course(course)
                .build();
        CourseLike savedCourseLike = courseLikeRepository.saveAndFlush(courseLike);
        em.clear();
        // when
        boolean result = courseService.changeCourseLikeState(course.getId(), memberId);
        em.flush();
        em.clear();
        Optional<CourseLike> resultOpCourseLike = courseRepository.findCourseLikesByCourseId(course.getId(), memberId);
        // then
        assertFalse(result);
        assertThat(resultOpCourseLike).isNotEmpty();
        assertFalse(resultOpCourseLike.get().getActive().isValue());
    }

    @DisplayName("코스가 이미 좋아요 취소 되어 있으면 좋아요")
    @Test
    void changeCourseLikeState3() {
        // given
        String memberId = "memberId";
        Course course = savedBeforeCourses.get(0);
        CourseLike courseLike = CourseLike.builder()
                .memberId(memberId)
                .course(course)
                .build();
        CourseLike savedCourseLike = courseLikeRepository.saveAndFlush(courseLike);
        em.clear();
        // when
        courseService.changeCourseLikeState(course.getId(), memberId);
        em.flush();
        em.clear();
        boolean result = courseService.changeCourseLikeState(course.getId(), memberId);
        em.flush();
        em.clear();
        Optional<CourseLike> resultOpCourseLike = courseRepository.findCourseLikesByCourseId(course.getId(), memberId);
        // then
        assertTrue(result);
        assertThat(resultOpCourseLike).isNotEmpty();
        assertTrue(resultOpCourseLike.get().getActive().isValue());
    }
}