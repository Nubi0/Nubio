package com.safeservice.domain.safebell.service.impl;

import com.safeservice.domain.safebell.entity.SafeBell;
import com.safeservice.domain.safebell.repository.SafeBellRepository;
import com.safeservice.domain.safebell.service.SafeBellService;
import com.safeservice.domain.safebell.type.Address;
import com.safeservice.domain.safebell.type.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SafeBellImplTest {

    @Autowired
    SafeBellService safeBellService;

    @Autowired
    SafeBellRepository safeBellRepository;

    private SafeBell beforeSafeBell;

    @BeforeEach
    void setUp(){
        SafeBell safeBell = SafeBell.builder()
                .position(Position.of(127.0495556, 37.514575))
                .address(Address.from("서울시 강남구"))
                .build();
        beforeSafeBell = safeBellRepository.save(safeBell);
    }



    @DisplayName("새로운 안전벨 등록 성공한다.")
    @Test
    void register() {
        // given
        SafeBell safeBell = SafeBell.builder()
                .position(Position.of(127.0495556, 37.514575))
                .address(Address.from("서울시 강남구"))
                .build();

        // when
        SafeBell registeredSafeBell = safeBellService.register(safeBell);

        // then
        assertThat(registeredSafeBell.getAddress()).isEqualTo(safeBell.getAddress());
        assertThat(registeredSafeBell.getPosition()).isEqualTo(safeBell.getPosition());

    }


}