package com.enjoyservice.domain.placefavorite.repository;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import com.enjoyservice.domain.placefavorite.entity.PlaceFavorite;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PlaceFavoriteRepositoryTest {

    @Autowired
    private PlaceFavoriteRepository placeFavoriteRepository;

    @Autowired
    private PlaceRepository placeRepository;

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

    @DisplayName("member가 즐겨찾기 한 Place가 있다면 memberId로 true를 확인할 수 있다.")
    @Test
    void existsByMemberId() {
        // given
        Place place = generatePlace(0, GroupCode.CE7, GroupName.카페);
        Place savedPlace = placeRepository.saveAndFlush(place);

        String memberId =  "member123";

        PlaceFavorite placeFavorite = PlaceFavorite.builder()
                .memberId(memberId)
                .place(place)
                .build();
        PlaceFavorite savedPlaceFavorite = placeFavoriteRepository.saveAndFlush(placeFavorite);
        // when
        boolean result = placeFavoriteRepository.existsByMemberIdAndPlace(memberId, savedPlace);
        // then
        assertThat(result).isTrue();
    }

    @DisplayName("member가 즐겨찾기 한 Place가 없다면 memberId로 false를 확인할 수 있다.")
    @Test
    void notExistsByMemberId() {
        // given
        Place place = generatePlace(0, GroupCode.CE7, GroupName.카페);
        Place savedPlace = placeRepository.saveAndFlush(place);

        String targetMemberId = "targetMember123";

        String memberId =  "member123";
        PlaceFavorite placeFavorite = PlaceFavorite.builder()
                .memberId(memberId)
                .place(place)
                .build();
        PlaceFavorite savedPlaceFavorite = placeFavoriteRepository.saveAndFlush(placeFavorite);
        // when
        boolean result = placeFavoriteRepository.existsByMemberIdAndPlace(targetMemberId, savedPlace);
        // then
        assertThat(result).isFalse();
    }
}