package com.enjoyservice.domain.placelike.entity;

import com.enjoyservice.domain.place.entity.Place;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PlaceLikeTest {

    @DisplayName("PlaceLike의 Active 값 전환 : true -> false")
    @Test
    void changeActiveValue() {
        // given
        PlaceLike placeLike = PlaceLike.builder()
                .place(Place.builder().build())
                .memberId("test")
                .build();
        // when
        boolean result = placeLike.changeActiveValue();
        // then
        assertThat(result).isFalse();
    }

    @DisplayName("PlaceLike의 Active 값 전환 : false -> true")
    @Test
    void changeActiveValue2() {
        // given
        PlaceLike placeLike = PlaceLike.builder()
                .place(Place.builder().build())
                .memberId("test")
                .build();
        // when
        placeLike.changeActiveValue();
        boolean result = placeLike.changeActiveValue();
        // then
        assertThat(result).isTrue();
    }
}