package com.enjoyservice.api.place.service;

import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.domain.placefavorite.service.service.PlaceFavoriteService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaceApiServiceUnitTest {

    @InjectMocks
    private PlaceApiService placeApiService;
    @Mock
    private PlaceService placeService;
    @Mock
    private PlaceLikeService placeLikeService;
    @Mock
    private PlaceFavoriteService placeFavoriteService;

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

    @DisplayName("장소 상세 조회")
    @Test
    void getPlaceInfo() {
        // given
        String memberId = "memberId0";
        Long mockPlaceId = 1L;
        Place mockPlace = generatePlace(1, GroupCode.CD7, GroupName.카페);
        List<Place> places = java.util.List.of(mockPlace);

        List<PlaceLike> likes = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            PlaceLike placeLike = PlaceLike.builder()
                    .memberId("memberId" + i)
                    .place(mockPlace)
                    .build();
            likes.add(placeLike);
        }
        //Mocking
        given(placeService.findOneByIdFetchImage(mockPlaceId))
                .willReturn(places);
        when(placeLikeService.findAllByPlace(any(Place.class)))
                        .thenReturn(likes);
        given(placeFavoriteService.existsByMemberIdAndPlace(memberId, mockPlace))
                .willReturn(true);

        // when
        PlaceInfoRes result = placeApiService.getPlaceInfo(memberId, mockPlaceId);
        // then
        assertThat(result.getPlaceInfo().getCategoryGroupCode()).isEqualTo(mockPlace.getCategory().getGroupCode().toString());
        assertThat(result.getPlaceInfo().getPlaceUrl()).isEqualTo("abc1.nubio.com");
        assertThat(result.getMeta().isLikeFlag()).isTrue();
        assertThat(result.getMeta().isFavoriteFlag()).isTrue();
    }
}