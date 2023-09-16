package com.enjoyservice.domain.placelike.service.impl;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import com.enjoyservice.domain.placelike.repository.PlaceLikeRepository;
import com.enjoyservice.domain.placelike.service.PlaceLikeService;
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
class PlaceLikeServiceImplTest {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private PlaceLikeRepository placeLikeRepository;
    @Autowired
    private PlaceLikeService placeLikeService;
    @Autowired
    private EntityManager em;

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
        List<PlaceLike> results = placeLikeService.findAllByPlaceAndActiveIsTrue(savedPlace);
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

    @DisplayName("장소를 좋아요 한 적 없으면 새로 생성함")
    @Test
    void changePlaceLikeState1() {
        // given
        String memberId = "testMemberId";
        Place place = generatePlace(0, GroupCode.CD7, GroupName.카페);
        Place savedPlace = placeRepository.saveAndFlush(place);
        em.flush();
        em.clear();
        List<PlaceLike> beforePlaceLikes = placeLikeRepository.findAll();
        int beforePlaceLikeCount = beforePlaceLikes.size();
        // when
        boolean result = placeLikeService.changePlaceLikeState(memberId, savedPlace);
        em.flush();
        em.clear();
        List<PlaceLike> savedPlaceLikes = placeLikeRepository.findAll();
        int resultPlaceLikeCount = savedPlaceLikes.size();
        // then
        assertThat(result).isTrue();
        assertThat(resultPlaceLikeCount).isEqualTo(beforePlaceLikeCount + 1);
    }

    @DisplayName("장소가 이미 좋아요 되어 있으면 좋아요 취소")
    @Test
    void changePlaceLikeState2() {
        // given
        String memberId = "testMemberId";
        Place place = generatePlace(0, GroupCode.CD7, GroupName.카페);
        Place savedPlace = placeRepository.saveAndFlush(place);
        em.clear();

        PlaceLike placeLike = PlaceLike.builder()
                .place(savedPlace)
                .memberId(memberId)
                .build();
        PlaceLike savedPlaceLike = placeLikeRepository.saveAndFlush(placeLike);
        em.clear();
        List<PlaceLike> beforePlaceLikes = placeLikeRepository.findAll();
        int beforePlaceLikeCount = beforePlaceLikes.size();
        em.flush();
        em.clear();
        // when
        boolean result = placeLikeService.changePlaceLikeState(memberId, savedPlace);
        List<PlaceLike> savedPlaceLikes = placeLikeRepository.findAll();
        int resultPlaceLikeCount = savedPlaceLikes.size();
        // then
        assertThat(result).isFalse();
        assertThat(resultPlaceLikeCount).isEqualTo(beforePlaceLikeCount);
    }

    @DisplayName("장소가 이미 좋아요 취소 되어 있으면 좋아요")
    @Test
    void changePlaceLikeState3() {
        // given
        String memberId = "testMemberId";
        Place place = generatePlace(0, GroupCode.CD7, GroupName.카페);
        Place savedPlace = placeRepository.saveAndFlush(place);
        em.clear();

        PlaceLike placeLike = PlaceLike.builder()
                .place(savedPlace)
                .memberId(memberId)
                .build();
        PlaceLike savedPlaceLike = placeLikeRepository.saveAndFlush(placeLike);
        em.clear();
        List<PlaceLike> beforePlaceLikes = placeLikeRepository.findAll();
        int beforePlaceLikeCount = beforePlaceLikes.size();
        em.flush();
        em.clear();
        // when
        placeLikeService.changePlaceLikeState(memberId, savedPlace); // 취소됨
        boolean result = placeLikeService.changePlaceLikeState(memberId, savedPlace); // 다시 좋아요
        List<PlaceLike> savedPlaceLikes = placeLikeRepository.findAll();
        int resultPlaceLikeCount = savedPlaceLikes.size();
        // then
        assertThat(result).isTrue();
        assertThat(resultPlaceLikeCount).isEqualTo(beforePlaceLikeCount);
    }
}