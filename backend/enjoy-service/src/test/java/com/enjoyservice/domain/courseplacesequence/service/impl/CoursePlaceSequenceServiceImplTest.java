package com.enjoyservice.domain.courseplacesequence.service.impl;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.course.repository.CourseRepository;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.courseplacesequence.entity.type.SequenceNumber;
import com.enjoyservice.domain.courseplacesequence.repository.CoursePlaceSequenceRepository;
import com.enjoyservice.domain.courseplacesequence.service.CoursePlaceSequenceService;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class CoursePlaceSequenceServiceImplTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CoursePlaceSequenceService coursePlaceSequenceService;

    @Autowired
    private EntityManager em;

    private List<Place> savedBeforePlaces;

    @BeforeEach
    void setUp() {
        /* 장소 9개 생성 */
        List<Place> beforePlaces = new ArrayList<>();
        // 편의점 3개 만들기
        for(int i = 0; i < 3; i++) {
            Place place = generatePlace(i, GroupCode.CS2, GroupName.편의점);
            beforePlaces.add(place);
        }
        // 음식점 3개 만들기
        for(int i = 3; i < 6; i++) {
            Place place = generatePlace(i, GroupCode.FD6, GroupName.음식점);
            beforePlaces.add(place);
        }
        // 카페 3개 만들기
        for(int i = 6; i < 9; i++) {
            Place place = generatePlace(i, GroupCode.CE7, GroupName.카페);
            beforePlaces.add(place);
        }
        // 9개 저장
        savedBeforePlaces = placeRepository.saveAllAndFlush(beforePlaces);
        em.clear();
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

    private Course generateCourse(int index) {
        return Course.builder()
                .title(Title.from("courseTitle" + index))
                .content(Content.from("courseContent" + index))
                .region(Region.DAEGU)
                .publicFlag(PublicFlag.from(true))
                .memberId("memberId" + index)
                .build();
    }

    @DisplayName("코스의 장소 순서 목록 저장")
    @Test
    void saveAll() {
        // given
        Course course = generateCourse(1);
        Course savedCourse = courseRepository.saveAndFlush(course);
        em.clear();
        final int targetSize = 3;
        List<CoursePlaceSequence> coursePlaceSequences = new ArrayList<>();
        for(int sequence = 1; sequence <= targetSize; sequence++) {
            CoursePlaceSequence coursePlaceSequence = CoursePlaceSequence.builder()
                    .sequenceNumber(SequenceNumber.from(sequence))
                    .place(savedBeforePlaces.get(sequence))
                    .course(savedCourse)
                    .build();
            coursePlaceSequences.add(coursePlaceSequence);
        }
        // when
        List<CoursePlaceSequence> result = coursePlaceSequenceService.saveAll(coursePlaceSequences);
        em.flush();
        em.clear();
        log.info("CoursePlaceSequence 저장");
        // then
        assertThat(result).hasSize(targetSize)
                .extracting("course", "place")
                .containsExactlyInAnyOrder(
                        tuple(savedCourse, savedBeforePlaces.get(1)),
                        tuple(savedCourse, savedBeforePlaces.get(2)),
                        tuple(savedCourse, savedBeforePlaces.get(3))
                );
    }
}