package com.enjoyservice.domain.course.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courselike.repository.CourseLikeRepository;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.courseplacesequence.entity.type.SequenceNumber;
import com.enjoyservice.domain.courseplacesequence.repository.CoursePlaceSequenceRepository;
import com.enjoyservice.domain.coursetag.entity.CourseTag;
import com.enjoyservice.domain.coursetag.repository.CourseTagRepository;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import com.enjoyservice.domain.tag.entity.Tag;
import com.enjoyservice.domain.tag.repository.TagRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CourseRepositoryTest {

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
        for(int i = 1; i <= courseSize; i++) {
            Course course = generateCourse(i, Region.DAEGU);
            courses.add(course);
        }
        for(int i = courseSize + 1; i <= courseSize + 3; i++) {
            Course course = generateCourse(i, Region.BUSAN);
            courses.add(course);
        }
        savedBeforeCourses = courseRepository.saveAllAndFlush(courses);
        em.clear();

        // 장소 저장
        int placeSize = 5;
        List<Place> places = new ArrayList<>();
        for(int i = 1; i <= placeSize; i++) {
            Place place = generatePlace(i, GroupCode.CE7, GroupName.카페);
            places.add(place);
        }
        savedBeforePlaces = placeRepository.saveAllAndFlush(places);
        em.clear();

        // CoursePlaceSequence 저장
        // 1번 코스에 장소 3개 추가
        List<CoursePlaceSequence> coursePlaceSequences = new ArrayList<>();
        for(int seq = 1; seq <= 3; seq++) {
            CoursePlaceSequence coursePlaceSequence = CoursePlaceSequence.builder()
                    .place(savedBeforePlaces.get(seq - 1))
                    .course(savedBeforeCourses.get(0))
                    .sequenceNumber(SequenceNumber.from(seq))
                    .build();
            coursePlaceSequences.add(coursePlaceSequence);
        }
        // 2번 코스에 장소 2개 추가
        for(int seq = 1; seq <= 2; seq++) {
            CoursePlaceSequence coursePlaceSequence = CoursePlaceSequence.builder()
                    .place(savedBeforePlaces.get(seq + 2))
                    .course(savedBeforeCourses.get(1))
                    .sequenceNumber(SequenceNumber.from(seq))
                    .build();
            coursePlaceSequences.add(coursePlaceSequence);
        }
        savedBeforeCoursePlaceSequences = coursePlaceSequenceRepository.saveAllAndFlush(coursePlaceSequences);
        em.clear();
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
        List<Course> result = courseRepository.findAllByRegionFetchPlace(Region.from("DAEGU"), PageRequest.of(0, 5));
        // then
        assertThat(result).hasSize(2)
                .extracting("title.value", "content.value", "region")
                .containsExactlyInAnyOrder(
                        tuple("courseTitle1", "courseContent1", Region.DAEGU),
                        tuple("courseTitle2", "courseContent2", Region.DAEGU)
                );
    }

    @DisplayName("Region으로 Course 전체 개수 구하기")
    @Test
    void countAllByRegion() {
        // given
        Region region = Region.from("DAEGU");
        // when
        Long result = courseRepository.countAllByRegion(region);
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
        for(int i = 1; i <= tagCount; i++) {
            Tag tag = Tag.from("tag" + i);
            tags.add(tag);
        }
        List<Tag> savedTags = tagRepository.saveAllAndFlush(tags);
        em.clear();

        List<CourseTag> courseTags = new ArrayList<>();
        for(Tag tag : savedTags) {
            CourseTag courseTag = CourseTag.builder()
                    .course(course)
                    .tag(tag)
                    .build();
            courseTags.add(courseTag);
        }
        List<CourseTag> savedCourseTags = courseTagRepository.saveAllAndFlush(courseTags);
        em.clear();
        // when
        List<Tag> result = courseRepository.findTagsByCourse(course);
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
        for(String memberId : memberIds) {
            CourseLike courseLike = CourseLike.builder()
                    .course(course)
                    .memberId(memberId)
                    .build();
            courseLikes.add(courseLike);
        }
        List<CourseLike> savedCourseLikes = courseLikeRepository.saveAllAndFlush(courseLikes);
        em.clear();
        // when
        List<CourseLike> result = courseRepository.findCourseLikesByCourse(course);
        // then
        assertThat(result).hasSize(memberIds.size())
                .extracting("course.title.value", "memberId")
                .containsExactlyInAnyOrder(
                        tuple("courseTitle1", memberId1),
                        tuple("courseTitle1", memberId2),
                        tuple("courseTitle1", memberId3)
                );
    }

    @DisplayName("CourseId로 연관된 Tag 모두 조회하기")
    @Test
    void findTagsByCourseId() {
        // given
        Course course = savedBeforeCourses.get(0);
        int tagCount = 5;
        List<Tag> tags = new ArrayList<>();
        for(int i = 1; i <= tagCount; i++) {
            Tag tag = Tag.from("tag" + i);
            tags.add(tag);
        }
        List<Tag> savedTags = tagRepository.saveAllAndFlush(tags);
        em.clear();

        List<CourseTag> courseTags = new ArrayList<>();
        for(Tag tag : savedTags) {
            CourseTag courseTag = CourseTag.builder()
                    .course(course)
                    .tag(tag)
                    .build();
            courseTags.add(courseTag);
        }
        List<CourseTag> savedCourseTags = courseTagRepository.saveAllAndFlush(courseTags);
        em.clear();
        // when
        List<Course> result = courseRepository.findCourseAndTagsByCourseId(course.getId());
        // then
        assertThat(result.size()).isEqualTo(tagCount);
//        assertThat(result.get(0).getCourseTags().get(0).getTag().getName().getValue()).isEqualTo("tag1");
//        assertThat(result.get(0).getCourseTags().get(1).getTag().getName().getValue()).isEqualTo("tag2");
//        assertThat(result.get(0).getCourseTags().get(2).getTag().getName().getValue()).isEqualTo("tag3");
//        assertThat(result.get(0).getCourseTags().get(3).getTag().getName().getValue()).isEqualTo("tag4");
//        assertThat(result.get(0).getCourseTags().get(4).getTag().getName().getValue()).isEqualTo("tag5");
    }

    @DisplayName("Course에 속한 Place 목록 조회")
    @Test
    void findPlacesByCourse() {
        // given
        Course course = savedBeforeCourses.get(0);
        Place place1 = savedBeforePlaces.get(0);
        Place place2 = savedBeforePlaces.get(1);
        Place place3 = savedBeforePlaces.get(2);
        // when
        List<Course> courses = courseRepository.findPlacesByCourse(course);
        List<Place> result = courses.get(0).getCoursePlaceSequences().stream()
                .map(CoursePlaceSequence::getPlace)
                .toList();
        // then
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName().getValue()).isEqualTo(place1.getName().getValue());
        assertThat(result.get(0).getSequences().get(0).getSequenceNumber().getValue()).isEqualTo(1);
        assertThat(result.get(1).getName().getValue()).isEqualTo(place2.getName().getValue());
        assertThat(result.get(1).getSequences().get(0).getSequenceNumber().getValue()).isEqualTo(2);
        assertThat(result.get(2).getName().getValue()).isEqualTo(place3.getName().getValue());
        assertThat(result.get(2).getSequences().get(0).getSequenceNumber().getValue()).isEqualTo(3);
    }

    @DisplayName("courseId, memberId로 CourseLike 조회(active 상관 없이)")
    @Test
    void findCourseLikesByCourseId() {
        // given
        Course course = savedBeforeCourses.get(0);
        Long courseId = course.getId();
        String memberId = "memberId";
        List<String> memberIds = List.of(memberId, "memberId1", "memberId2", "memberId3");
        for(String id : memberIds) {
            CourseLike courseLike = CourseLike.builder()
                    .course(course)
                    .memberId(id)
                    .build();
            courseLikeRepository.saveAndFlush(courseLike);
        }
        // when
        Optional<CourseLike> opCourseLike = courseRepository.findCourseLikesByCourseId(courseId, memberId);
        // then
        assertThat(opCourseLike).isNotEmpty();
        assertThat(opCourseLike.get().getCourse().getId()).isEqualTo(courseId);
        assertThat(opCourseLike.get().getMemberId()).isEqualTo(memberId);
    }

    @DisplayName("내가 만든 코스 목록 조회(fetch join)")
    @Test
    void findMyCourses() {
        // given
        int memberNum = 100;
        String memberId = "memberId" + memberNum;
        Course course = generateCourse(memberNum, Region.DAEGU);
        Course savedCourse = courseRepository.saveAndFlush(course);
        log.info("코스 저장 완료");
        em.clear();
        List<CoursePlaceSequence> savedSequences = new ArrayList<>();
        int idx = 1;
        for(Place place : savedBeforePlaces) {
            CoursePlaceSequence coursePlaceSequence = CoursePlaceSequence.builder()
                    .sequenceNumber(SequenceNumber.from(idx++))
                    .place(place)
                    .course(savedCourse)
                    .build();
            CoursePlaceSequence savedSequence = coursePlaceSequenceRepository.saveAndFlush(coursePlaceSequence);
            savedSequences.add(savedSequence);
        }
        log.info("코스-장소 순서 저장 완료");
        em.clear();
        Tag tag1 = Tag.from("tag1");
        Tag savedTag1 = tagRepository.saveAndFlush(tag1);
        Tag tag2 = Tag.from("tag2");
        Tag savedTag2 = tagRepository.saveAndFlush(tag2);
        em.clear();
        CourseTag courseTag1 = CourseTag.builder()
                .course(course)
                .tag(tag1)
                .build();
        CourseTag savedCourseTag1 = courseTagRepository.saveAndFlush(courseTag1);
        CourseTag courseTag2 = CourseTag.builder()
                .course(course)
                .tag(tag2)
                .build();
        CourseTag savedCourseTag2 = courseTagRepository.saveAndFlush(courseTag2);
        log.info("코스 태그 저장 완료");
        em.clear();
        // when
        Page<Course> result = courseRepository.findMyCourses(memberId, PageRequest.of(0, 10));
        log.info("내가 만든 코스 목록 조회 완료");
        // then
        List<Course> courses = result.getContent();
        log.info("courses 크기 : {}", courses.size());
        for(Course c : courses) {
            log.info("courseId : {}", c.getId());
        }


        assertThat(result.getContent()).hasSize(1);
    }
}