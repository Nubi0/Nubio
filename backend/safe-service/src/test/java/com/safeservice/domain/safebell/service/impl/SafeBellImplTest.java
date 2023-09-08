package com.safeservice.domain.safebell.service.impl;

import com.safeservice.domain.safebell.entity.SafeBell;
import com.safeservice.domain.safebell.exception.InvalidAddressFormatException;
import com.safeservice.domain.safebell.repository.SafeBellRepository;
import com.safeservice.domain.safebell.service.SafeBellService;
import com.safeservice.domain.safebell.type.Address;
import com.safeservice.domain.safebell.type.Position;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

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
    void setUp() {
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

    @DisplayName("주소 길이 제한 예외발생")
    @ParameterizedTest
    @ValueSource(ints = {256, 260, 700})
    void failAddressNameTest1(int length) {
        // given
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("a");
        }

        // when, then
        assertThatThrownBy(() -> {
            Address.from(sb.toString());
        }).isInstanceOf(InvalidAddressFormatException.class);
    }

    @DisplayName("주소 길이 제한이내 정상 작동")
    @ParameterizedTest
    @ValueSource(ints = {51, 60, 255})
    void successAddressNameTest(int length) {
        // given
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append("a");
        }

        // when, then
        assertThatCode(() -> {
            Address.from(sb.toString());
        }).doesNotThrowAnyException();

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

    @DisplayName("거리이내의 안전벨 목록 출력")
    @Test
    void findWithinDistance() {
        // given
        SafeBell beforeSafeBell1 = safeBellRepository.save(SafeBell.builder()
                .position(Position.of(126.9052383, 37.5157702))
                .address(Address.from("영등포역"))
                .build());
        SafeBell beforeSafeBell2 = safeBellRepository.save(SafeBell.builder()
                .position(Position.of(126.8890174, 37.5088141))
                .address(Address.from("신도림역"))
                .build());
        SafeBell beforeSafeBell3 = safeBellRepository.save(SafeBell.builder()
                .position(Position.of(126.9221228, 37.5215737))
                .address(Address.from("여의도역"))
                .build());

        // when
        List<SafeBell> withinList = safeBellService.findWithinDistance(126.9019532, 37.5170112, 2000);

        // then
        assertThat(withinList).hasSize(3)
                .extracting("id", "position.longitude", "position.latitude")
                .containsExactlyInAnyOrder(
                        tuple(beforeSafeBell1.getId(), beforeSafeBell1.getPosition().getLongitude(), beforeSafeBell1.getPosition().getLatitude()),
                        tuple(beforeSafeBell2.getId(), beforeSafeBell2.getPosition().getLongitude(), beforeSafeBell2.getPosition().getLatitude()),
                        tuple(beforeSafeBell3.getId(), beforeSafeBell3.getPosition().getLongitude(), beforeSafeBell3.getPosition().getLatitude())
                );
    }

    @DisplayName("거리이내의 안전벨 없을때")
    @Test
    void findWithinNotDistance() {
        // given
        SafeBell beforeSafeBell4 = safeBellRepository.save(SafeBell.builder()
                .position(Position.of(126.8927728, 37.4925085))
                .address(Address.from("대림역"))
                .build());
        SafeBell beforeSafeBell5 = safeBellRepository.save(SafeBell.builder()
                .position(Position.of(126.9347011, 37.5551399))
                .address(Address.from("신촌역"))
                .build());

        // when
        List<SafeBell> withinList = safeBellService.findWithinDistance(126.9019532, 37.5170112, 2000);

        // then
        assertThat(withinList).hasSize(0);
    }

}