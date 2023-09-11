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
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void findWithinDistance() {
    }
}