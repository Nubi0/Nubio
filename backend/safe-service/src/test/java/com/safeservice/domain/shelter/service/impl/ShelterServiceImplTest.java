package com.safeservice.domain.shelter.service.impl;

import com.safeservice.domain.shelter.constant.ShelterType;
import com.safeservice.domain.shelter.entity.Shelter;
import com.safeservice.domain.shelter.mongo.ShelterRepository;
import com.safeservice.domain.shelter.service.ShelterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


@SpringBootTest
@Transactional
class ShelterServiceImplTest {

    @Autowired
    ShelterService shelterService;

    @Autowired
    ShelterRepository shelterRepository;

    @DisplayName("새로운 보호소 등록 성공한다.")
    @Test
    void register() {

        // given
        Shelter shelter = Shelter.builder()
                .address("학교")
                .phoneNumber("010-0000-0000")
                .shelterType(ShelterType.SCHOOL)
                .location(new Point(-63.616672, -38.416097))
                .name("서울대학교")
                .build();

        // when
        Shelter registered = shelterService.register(shelter);

        // then
        assertThat(registered.getAddress()).isEqualTo(shelter.getAddress());
        assertThat(registered.getPhoneNumber()).isEqualTo(shelter.getPhoneNumber());
        assertThat(registered.getShelterType()).isEqualTo(shelter.getShelterType());
        assertThat(registered.getName()).isEqualTo(shelter.getName());
        assertThat(registered.getLocation().getX()).isEqualTo(shelter.getLocation().getX());
        assertThat(registered.getLocation().getY()).isEqualTo(shelter.getLocation().getY());

    }

    @DisplayName("2KM 거리이내의 보호소 목록 출력")
    @Test
    void findShelterNear() {
        // given
        Shelter shelter1 = shelterService.register(Shelter.builder()
                .address("학교")
                .phoneNumber("010-0000-0000")
                .shelterType(ShelterType.SCHOOL)
                .location(new Point(-63.616672, -38.416097))
                .name("서울대학교")
                .build());

        Shelter shelter2 = shelterService.register(Shelter.builder()
                .address("병원")
                .shelterType(ShelterType.HOSPITAL)
                .location(new Point(-63.616672, -38.416097))
                .name("서울대학교 병원")
                .build());

        Shelter shelter3 = shelterService.register(Shelter.builder()
                .address("소방서")
                .phoneNumber("010-0000-0000")
                .shelterType(ShelterType.FIRE_STATION)
                .location(new Point(-63.616672, -38.416097))
                .name("서울 소방서")
                .build());

        // when
        List<Shelter> shelterNear = shelterService.findShelterNear(new Point(-63.616672, -38.416097), new Distance(2, Metrics.KILOMETERS), ShelterType.HOSPITAL);

        // then
        assertThat(shelterNear)
                .hasSize(1)
                .extracting("id", "address", "location.x", "location.y", "name", "phoneNumber")
                .containsExactlyInAnyOrder(
                        tuple(shelter2.getId(), shelter2.getAddress(), shelter2.getLocation().getX(), shelter2.getLocation().getY(), shelter2.getName(), shelter2.getPhoneNumber())
                );

    }

    @DisplayName("2KM 거리이내의 보호소 목록 페이징 출력")
    @Test
    void findShelterNearWithPaging() {
        // given
        // given

        double x = -63.616672;
        double y = -38.416097;

        int totalShelterSize = 20;

        for (int i = 0; i < totalShelterSize; i++) {
            shelterService.register(Shelter.builder()
                    .address("주소"+i)
                    .phoneNumber("010-0000-0000")
                    .shelterType(ShelterType.SCHOOL)
                    .location(new Point(x, y))
                    .name("이름"+i)
                    .build());
            x += 0.0000001;
            y += 0.0000001;
        }


        x = 126.8927728;
        y = 37.4925085;


        for (int i = 0; i < totalShelterSize; i++) {
            shelterService.register(Shelter.builder()
                    .address("주소"+i)
                    .phoneNumber("010-0000-0000")
                    .shelterType(ShelterType.SCHOOL)
                    .location(new Point(x, y))
                    .name("이름"+i)
                    .build());
            x += 0.0000001;
            y += 0.0000001;
        }

        // when
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");
        Page<Shelter> shelterNearWithPaging = shelterService.findShelterNearWithPaging(new Point(-63.616672, -38.416097), new Distance(2, Metrics.KILOMETERS), ShelterType.SCHOOL, pageable);
        List<Shelter> shelterNearWithPagingContent = shelterNearWithPaging.getContent();

        // then
        // 페이지당 데이터 개수
        assertThat(shelterNearWithPaging.getSize()).isEqualTo(pageSize);
        // 현재 페이지 번호 0부터 시작
        assertThat(shelterNearWithPaging.getNumber()).isEqualTo(0);
        // 총 몇 페이지
        assertThat(shelterNearWithPaging.getTotalPages()).isEqualTo(totalShelterSize / pageSize);
        // 총 몇 페이지
        assertThat(shelterNearWithPaging.getTotalElements()).isEqualTo(totalShelterSize);
        // 다음 페이지 존재 여부
        assertThat(shelterNearWithPaging.hasNext()).isTrue();
        // 시작 페이지(0) 여부
        assertThat(shelterNearWithPaging.isFirst()).isTrue();

        assertThat(shelterNearWithPagingContent).hasSize(pageSize);
    }
}