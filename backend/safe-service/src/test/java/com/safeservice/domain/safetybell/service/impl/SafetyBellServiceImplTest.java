package com.safeservice.domain.safetybell.service.impl;

import org.springframework.data.geo.Point;
import com.safeservice.domain.safetybell.entity.SafetyBell;
import com.safeservice.domain.safetybell.mongo.SafetyBellRepository;
import com.safeservice.domain.safetybell.service.SafetyBellService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
@Transactional
class SafetyBellServiceImplTest {

    @Autowired
    SafetyBellRepository safetyBellRepository;

    @Autowired
    SafetyBellService safetyBellService;

    @Autowired
    private EntityManager entityManager;


    @DisplayName("새로운 안전벨 등록 성공한다.")
    @Test
    void register() {
        // given
        SafetyBell safetyBell = SafetyBell.builder()
                .address("착한피자")
                .location(new Point(127.080, 37.251))
                .build();

        SafetyBell registered = safetyBellService.register(safetyBell);

        assertThat(registered.getAddress()).isEqualTo(safetyBell.getAddress());
        assertThat(registered.getLocation().getX()).isEqualTo(safetyBell.getLocation().getX());
        assertThat(registered.getLocation().getY()).isEqualTo(safetyBell.getLocation().getY());
    }

    @Test
    void delete() {
    }

    @DisplayName("2KM 거리이내의 안전벨 목록 출력")
    @Test
    void findWithinDistance() {
        // given
        SafetyBell registered1 = safetyBellService.register(SafetyBell.builder()
                .address("영등포역")
                .location(new Point(126.9052383, 37.5157702))
                .build());

        SafetyBell registered2 = safetyBellService.register(SafetyBell.builder()
                .address("신도림역")
                .location(new Point(126.8890174, 37.5088141))
                .build());

        SafetyBell registered3 = safetyBellService.register(SafetyBell.builder()
                .address("여의도역")
                .location(new Point(126.9221228, 37.5215737))
                .build());

        //when
        List<SafetyBell> withinList = safetyBellService.findWithinDistance(126.9019532, 37.5170112, 2);

        // then
        assertThat(withinList).hasSize(3)
                .extracting("id", "address", "location.x", "location.y")
                .containsExactlyInAnyOrder(
                        tuple(registered1.getId(), registered1.getAddress(), registered1.getLocation().getX(), registered1.getLocation().getY()),
                        tuple(registered2.getId(), registered2.getAddress(), registered2.getLocation().getX(), registered2.getLocation().getY()),
                        tuple(registered3.getId(), registered3.getAddress(), registered3.getLocation().getX(), registered3.getLocation().getY())
                );
    }

    @DisplayName("2KM 거리이내의 안전벨 없을때")
    @Test
    void findWithinNotDistance() {
        // given
        SafetyBell registered1 = safetyBellService.register(SafetyBell.builder()
                .address("대림역")
                .location(new Point(126.8927728, 37.4925085))
                .build());

        SafetyBell registered2 = safetyBellService.register(SafetyBell.builder()
                .address("신촌역")
                .location(new Point(126.9347011, 37.5551399))
                .build());


        //when
        List<SafetyBell> withinList = safetyBellService.findWithinDistance(126.9019532, 37.5170112, 2);

        // then
        assertThat(withinList).hasSize(0);
    }
}