package com.enjoyservice.api.placelike.service.impl;

import com.enjoyservice.api.placelike.dto.PlaceLikeRes;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import com.enjoyservice.domain.placelike.service.PlaceLikeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaceLikeApiServiceImplUnitTest {

    @InjectMocks
    private PlaceLikeApiServiceImpl placeLikeApiServiceImpl;
    @Mock
    private PlaceLikeService placeLikeService;
    @Mock
    private PlaceService placeService;

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

    @DisplayName("장소를 좋아요 한 적 없으면 새로 생성함")
    @Test
    void firstLikePlace() {
        // given
        String memberId = "testMemberId";
        Place place = generatePlace(0, GroupCode.CD7, GroupName.카페);
        Long placeId = 1L;
        List<PlaceLike> placeLikes = new ArrayList<>();
        long placeLikeSize = 5;
        for(int i = 0; i < placeLikeSize; i++) {
            PlaceLike placeLike = PlaceLike.builder()
                    .memberId("member")
                    .place(place)
                    .build();
            placeLikes.add(placeLike);
        }
        // mocking
        when(placeService.findById(placeId))
                .thenReturn(place);
        when(placeLikeService.changePlaceLikeState(memberId, place))
                .thenReturn(true);
        when(placeLikeService.findAllByPlaceAndActiveIsTrue(place))
                .thenReturn(placeLikes);
        // when
        PlaceLikeRes placeLikeRes = placeLikeApiServiceImpl.likePlace(memberId, placeId);
        // then
        assertThat(placeLikeRes.getLikeCount()).isEqualTo(placeLikeSize);
        assertThat(placeLikeRes.isLikeFlag()).isTrue();
    }

    @DisplayName("좋아요 했던 장소를 좋아요 취소")
    @Test
    void likePlace1() {
        // given
        String memberId = "testMemberId";
        Place place = generatePlace(0, GroupCode.CD7, GroupName.카페);
        Long placeId = 1L;
        List<PlaceLike> placeLikes = new ArrayList<>();
        long placeLikeSize = 5;
        for(int i = 0; i < placeLikeSize; i++) {
            PlaceLike placeLike = PlaceLike.builder()
                    .memberId("member")
                    .place(place)
                    .build();
            placeLikes.add(placeLike);
        }
        PlaceLike placeLike = PlaceLike.builder()
                .memberId(memberId)
                .place(place)
                .build();
        placeLikes.add(placeLike);
        // mocking
        when(placeService.findById(placeId))
                .thenReturn(place);
        when(placeLikeService.changePlaceLikeState(memberId, place))
                .thenReturn(false);
        when(placeLikeService.findAllByPlaceAndActiveIsTrue(place))
                .thenReturn(placeLikes);
        // when
        PlaceLikeRes placeLikeRes = placeLikeApiServiceImpl.likePlace(memberId, placeId);
        // then
        assertThat(placeLikeRes.getLikeCount()).isEqualTo(placeLikeSize + 1);
        assertThat(placeLikeRes.isLikeFlag()).isFalse();
    }

    @DisplayName("좋아요 취소 했던 장소를 다시 좋아요")
    @Test
    void likePlace2() {
        // given
        String memberId = "testMemberId";
        Place place = generatePlace(0, GroupCode.CD7, GroupName.카페);
        Long placeId = 1L;
        List<PlaceLike> placeLikes = new ArrayList<>();
        long placeLikeSize = 5;
        for(int i = 0; i < placeLikeSize; i++) {
            PlaceLike placeLike = PlaceLike.builder()
                    .memberId("member")
                    .place(place)
                    .build();
            placeLikes.add(placeLike);
        }
        PlaceLike placeLike = PlaceLike.builder()
                .memberId(memberId)
                .place(place)
                .build();
        placeLike.changeActiveValue();
        placeLikes.add(placeLike);

        // mocking
        when(placeService.findById(placeId))
                .thenReturn(place);
        when(placeLikeService.changePlaceLikeState(memberId, place))
                .thenReturn(true);
        when(placeLikeService.findAllByPlaceAndActiveIsTrue(place))
                .thenReturn(placeLikes);
        // when
        PlaceLikeRes placeLikeRes = placeLikeApiServiceImpl.likePlace(memberId, placeId);
        // then
        assertThat(placeLikeRes.getLikeCount()).isEqualTo(placeLikeSize + 1);
        assertThat(placeLikeRes.isLikeFlag()).isTrue();
    }
}