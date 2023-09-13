package com.safeservice.domain.facility.service;

import com.safeservice.domain.facility.constant.FacilityType;
import com.safeservice.domain.facility.entity.SafetyFacility;
import com.safeservice.domain.facility.mongo.SafetyFacilityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

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
                .location(new Point(126.9052383, 37.5157702))
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

    @Test
    void findFacilityNear() {
    }
}