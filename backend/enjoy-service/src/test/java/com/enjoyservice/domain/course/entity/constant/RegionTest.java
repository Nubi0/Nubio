package com.enjoyservice.domain.course.entity.constant;

import com.enjoyservice.domain.course.exception.InvalidRegionException;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.exception.InvalidGroupNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    @DisplayName("Region이 맞으면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"DAEGU"})
    void isGroupName(String input) {
        assertTrue(Region.isRegion(input));
    }

    @DisplayName("Region이 아니면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"PYEONGYANG"})
    void isNotGroupName(String input) {
        assertFalse(Region.isRegion(input));
    }

    @DisplayName("Region이 맞으면 Region 객체를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"daegu", "DAEGU", "Daegu", "daeGu"})
    void from(String input) {
        // when
        Region region = Region.from(input);
        // then
        assertThat(region).isEqualTo(Region.valueOf(input.toUpperCase()));
    }

    @DisplayName("Region이 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"AB2", "P6", "7", "QWER"})
    void canNotFrom(String input) {
        // when then
        assertThatThrownBy(() -> Region.from(input))
                .isInstanceOf(InvalidRegionException.class);
    }
}