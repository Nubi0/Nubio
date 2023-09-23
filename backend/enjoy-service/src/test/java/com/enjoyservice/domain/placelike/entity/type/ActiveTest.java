package com.enjoyservice.domain.placelike.entity.type;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ActiveTest {

    @DisplayName("Active 상태 변환 결과 : true -> false")
    @Test
    void changeValue() {
        // given
        Active active = Active.from(true);
        // when
        active.changeValue();
        // then
        assertThat(active.isValue()).isFalse();
    }

    @DisplayName("Active 상태 변환 결과 : false -> true")
    @Test
    void changeValue2() {
        // given
        Active active = Active.from(false);
        // when
        active.changeValue();
        // then
        assertThat(active.isValue()).isTrue();
    }
}