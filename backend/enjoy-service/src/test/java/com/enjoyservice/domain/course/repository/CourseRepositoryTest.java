package com.enjoyservice.domain.course.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.courseplacesequence.entity.type.SequenceNumber;
import com.enjoyservice.domain.courseplacesequence.repository.CoursePlaceSequenceRepository;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
            Place place = generatePlace(i, GroupCode.CD7, GroupName.카페);
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
        Long result = courseRepository.countAllByRegion(region);
        // then
        assertThat(result).isEqualTo(2);
    }
}