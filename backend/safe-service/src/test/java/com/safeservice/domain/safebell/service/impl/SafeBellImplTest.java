package com.safeservice.domain.safebell.service.impl;

import com.safeservice.domain.safebell.entity.SafeBell;
import com.safeservice.domain.safebell.repository.SafeBellRepository;
import com.safeservice.domain.safebell.service.SafeBellService;
import com.safeservice.domain.safebell.type.Address;
import com.safeservice.domain.safebell.type.Position;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SafeBellImplTest {

    @Autowired
    SafeBellService safeBellService;

    @Autowired
    SafeBellRepository safeBellRepository;

    @Autowired
    private EntityManager entityManager;

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

    @DisplayName("논리적 삭제 성공한다.")
    @Test
    void delete() {
        // given
        assertThat(beforeSafeBell.getId()).isNotNull();
        assertThat(beforeSafeBell.getActive().isValue()).isTrue();

        // when
        safeBellService.delete(beforeSafeBell);
        Optional<SafeBell> afterDelete = safeBellRepository.findById(beforeSafeBell.getId());

        // then
        assertThat(afterDelete).isEmpty();
    }

    @DisplayName("active = true가 where문에 자동으로 들어간다.")
    @Test
    void deleteWhereId() {
        // given
        assertThat(beforeSafeBell.getId()).isNotNull();
        assertThat(beforeSafeBell.getActive().isValue()).isTrue();

        // when
        safeBellService.delete(beforeSafeBell);
        List<SafeBell> resultList = entityManager.createQuery("SELECT sb FROM SafeBell sb WHERE id = :id", SafeBell.class)
                .setParameter("id", beforeSafeBell.getId())
                .getResultList();

        // then
        assertThat(resultList).isEmpty();
    }

}