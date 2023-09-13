package com.enjoyservice.domain.placelike.service.impl;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import com.enjoyservice.domain.placelike.repository.PlaceLikeRepository;
import com.enjoyservice.domain.placelike.service.PlaceLikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PlaceLikeServiceImplTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceLikeRepository placeLikeRepository;

    @Autowired
    private PlaceLikeService placeLikeService;

    private Place generatePlace(int index, GroupCode groupCode, GroupName groupName) {
        return Place.builder()
                .kakaoId(KakaoId.from(index))
                .name(Name.from("place" + index))
                .category(Category.from(groupCode, groupName, Detail.from("category detail" + index)))
                .phone(Phone.from("010-1111-111" + index))
                .url(Url.from("abc" + index + ".nubio.com"))
                .address(Address.builder()
                        .name("주소" + index)
                        .roadName(RoadName.from("도로명주소" + index)).build()
                )
                .position(Position.builder()
                        .latitude(Latitude.from(Double.valueOf("37.263906325599" + index)))
                        .longitude(Longitude.from(Double.valueOf("127.032310032317" + index)))
                        .build())
                .active(Active.from(true))
                .build();
    }

    @DisplayName("Place로 해당 Place의 PlaceLike 모두 조회하기")
    @Test
    void findAllByPlace() {
        // given
        // 장소 1개 만들기
        Place place = generatePlace(0, GroupCode.CS2, GroupName.편의점);
        Place savedPlace = placeRepository.saveAndFlush(place);
        // 좋아요 처리
        int targetCount = 5; // 저장할 좋아요 개수
        for(int memberIndex = 0; memberIndex < targetCount; memberIndex++) {
            String memberId = "UUID" + memberIndex; // memberId 생성
            PlaceLike placeLike = PlaceLike.builder()
                    .memberId(memberId)
                    .place(savedPlace)
                    .build();
            placeLikeRepository.saveAndFlush(placeLike);
        }

        // when
        List<PlaceLike> results = placeLikeService.findAllByPlace(savedPlace);
        // then
        assertThat(results).hasSize(targetCount)
                .extracting("memberId", "place")
                .containsExactlyInAnyOrder(
                        tuple("UUID" + 0, savedPlace),
                        tuple("UUID" + 1, savedPlace),
                        tuple("UUID" + 2, savedPlace),
                        tuple("UUID" + 3, savedPlace),
                        tuple("UUID" + 4, savedPlace)
                );
    }
}