package com.safeservice.domain.facility.service;

import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.facility.mongo.SafetyFacilityRepository;
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
class SafetyFacilityServiceTest {

    @Autowired
    SafetyFacilityRepository safetyFacilityRepository;

    @Autowired
    SafetyFacilityService safetyFacilityService;

    @DisplayName("새로운 안전시설 등록 성공한다.")
    @Test
    void register() {
        // given
        SafetyFacility safetyFacility = SafetyFacility.builder()
                .address("영등포역")
                .location(new Point(-63.616672, -38.416097))
                .facilityType(FacilityType.SAFETY_BELL)
                .build();

        // when
        SafetyFacility registered = safetyFacilityService.register(safetyFacility);

        // then
        assertThat(registered.getAddress()).isEqualTo(safetyFacility.getAddress());
        assertThat(registered.getLocation().getX()).isEqualTo(safetyFacility.getLocation().getX());
        assertThat(registered.getLocation().getY()).isEqualTo(safetyFacility.getLocation().getY());
        assertThat(registered.getFacilityType().getDescription()).isEqualTo(safetyFacility.getFacilityType().getDescription());
    }

    @DisplayName("2KM 거리이내의 안전벨 목록 출력")
    @Test
    void findFacilityNear() {
        // given
        SafetyFacility safetyFacility1 = safetyFacilityService.register(SafetyFacility.builder()
                .address("영등포역")
                .location(new Point(-63.616672, -38.416097))
                .facilityType(FacilityType.SAFETY_BELL)
                .build());

        SafetyFacility safetyFacility2 = safetyFacilityService.register(SafetyFacility.builder()
                .address("신도림역")
                .location(new Point(-63.616673, -38.416097))
                .facilityType(FacilityType.SAFETY_BELL)
                .build());

        SafetyFacility safetyFacility3 = safetyFacilityService.register(SafetyFacility.builder()
                .address("여의도역")
                .location(new Point(-63.616674, -38.416097))
                .facilityType(FacilityType.SAFETY_BELL)
                .build());

        // when
        List<SafetyFacility> facilityNear = safetyFacilityService.findFacilityNear(new Point(-63.616672, -38.416097), new Distance(2, Metrics.KILOMETERS), FacilityType.SAFETY_BELL);

        // then
        assertThat(facilityNear)
                .hasSize(3)
                .extracting("id", "address", "location.x", "location.y")
                .containsExactlyInAnyOrder(
                        tuple(safetyFacility1.getId(), safetyFacility1.getAddress(), safetyFacility1.getLocation().getX(), safetyFacility1.getLocation().getY()),
                        tuple(safetyFacility2.getId(), safetyFacility2.getAddress(), safetyFacility2.getLocation().getX(), safetyFacility2.getLocation().getY()),
                        tuple(safetyFacility3.getId(), safetyFacility3.getAddress(), safetyFacility3.getLocation().getX(), safetyFacility3.getLocation().getY())
                );

    }

    @DisplayName("2KM 거리이내의 안전벨 목록 페이징 출력")
    @Test
    void findFacilityNearWithPaging() {
        // given

        double x = -63.616672;
        double y = -38.416097;

        int totalSafetyFacilitySize = 20;

        for (int i = 0; i < totalSafetyFacilitySize; i++) {
            safetyFacilityService.register(SafetyFacility.builder()
                    .address("주소" + i)
                    .location(new Point(x, y))
                    .facilityType(FacilityType.SAFETY_BELL)
                    .build());
            x += 0.0000001;
            y += 0.0000001;
        }

        x = 126.8927728;
        y = 37.4925085;

        for (int i = totalSafetyFacilitySize; i < totalSafetyFacilitySize*2; i++) {
            safetyFacilityService.register(SafetyFacility.builder()
                    .address("주소" + i)
                    .location(new Point(x, y))
                    .facilityType(FacilityType.SAFETY_BELL)
                    .build());
            x += 0.0000001;
            y += 0.0000001;
        }

        // when
        int pageNumber = 0;
        int pageSize = 10;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "id");
        Page<SafetyFacility> facilityNear = safetyFacilityService.findFacilityNearWithPaging(new Point(-63.616672, -38.416097), new Distance(2, Metrics.KILOMETERS), FacilityType.SAFETY_BELL, pageable);
        List<SafetyFacility> facilityNearContent = facilityNear.getContent();

        // then
        // 페이지당 데이터 개수
        assertThat(facilityNear.getSize()).isEqualTo(pageSize);
        // 현재 페이지 번호 0부터 시작
        assertThat(facilityNear.getNumber()).isEqualTo(0);
        // 총 몇 페이지
        assertThat(facilityNear.getTotalPages()).isEqualTo(totalSafetyFacilitySize / pageSize);
        // 총 몇 페이지
        assertThat(facilityNear.getTotalElements()).isEqualTo(totalSafetyFacilitySize);
        // 다음 페이지 존재 여부
        assertThat(facilityNear.hasNext()).isTrue();
        // 시작 페이지(0) 여부
        assertThat(facilityNear.isFirst()).isTrue();

        assertThat(facilityNearContent).hasSize(pageSize);
    }

}