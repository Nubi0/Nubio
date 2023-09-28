package com.enjoyservice.domain.place.service.impl;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import com.enjoyservice.domain.place.service.PlaceService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PlaceServiceImplTest {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private EntityManager em;

    private List<Place> savedBeforePlaces;

    @BeforeEach
    void setUp() {
        /* 장소 9개 생성 */
        List<Place> beforePlaces = new ArrayList<>();
        // 편의점 3개 만들기
        for (int i = 0; i < 3; i++) {
            Place place = generatePlace(i, GroupCode.CS2, GroupName.편의점);
            beforePlaces.add(place);
        }
        // 음식점 3개 만들기
        for (int i = 3; i < 6; i++) {
            Place place = generatePlace(i, GroupCode.FD6, GroupName.음식점);
            beforePlaces.add(place);
        }
        // 카페 3개 만들기
        for (int i = 6; i < 9; i++) {
            Place place = generatePlace(i, GroupCode.CE7, GroupName.카페);
            beforePlaces.add(place);
        }
        // 9개 저장
        savedBeforePlaces = placeRepository.saveAllAndFlush(beforePlaces);
        em.clear();
    }

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

    @DisplayName("장소 1개 조회에 성공한다.")
    @Test
    void findById() {
        // given
        Place place = generatePlace(9, GroupCode.CS2, GroupName.편의점);
        Place savedPlace = placeRepository.saveAndFlush(place);
        // when
        Place result = placeService.findById(savedPlace.getId());
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(savedPlace.getId());
    }

    @DisplayName("id 목록(모두 존재)으로 모든 Place 조회")
    @Test
    void findAllByIds() {
        // given
        List<Long> ids = List.of(savedBeforePlaces.get(0).getId(), savedBeforePlaces.get(1).getId(), savedBeforePlaces.get(2).getId());
        // when
        List<Place> result = placeService.findAllById(ids);
        // then
        assertThat(result).hasSize(3)
                .extracting("id", "name.value")
                .containsExactlyInAnyOrder(
                        tuple(savedBeforePlaces.get(0).getId(), savedBeforePlaces.get(0).getName().getValue()),
                        tuple(savedBeforePlaces.get(1).getId(), savedBeforePlaces.get(1).getName().getValue()),
                        tuple(savedBeforePlaces.get(2).getId(), savedBeforePlaces.get(2).getName().getValue())
                );
    }

    @DisplayName("id 목록(일부 존재 안함)으로 모든 Place 조회")
    @Test
    void findAllByIdsNotExist() {
        // given
        List<Long> ids = List.of(savedBeforePlaces.get(0).getId(), savedBeforePlaces.get(1).getId());
        // when
        List<Place> result = placeService.findAllById(ids);
        // then
        assertThat(result).hasSize(2)
                .extracting("id", "name.value")
                .containsExactlyInAnyOrder(
                        tuple(savedBeforePlaces.get(0).getId(), savedBeforePlaces.get(0).getName().getValue()),
                        tuple(savedBeforePlaces.get(1).getId(), savedBeforePlaces.get(1).getName().getValue())
                );
    }

    @DisplayName("id 목록(모두 존재 안함)으로 모든 Place 조회")
    @Test
    void findAllByIdsAllNotExist() {
        // given
        List<Long> notExistIds = List.of(1000000L, 1000001L, 1000002L);
        // when
        List<Place> result = placeService.findAllById(notExistIds);
        // then
        assertThat(result).hasSize(0);
    }

    @DisplayName("새로운 장소 1개 저장")
    @Test
    void register() {
        // given
        Place place = generatePlace(9, GroupCode.CS2, GroupName.편의점);

        // when
        Place registeredPlace = placeService.register(place);
        em.clear();

        // then
        assertThat(registeredPlace.getKakaoId().getValue()).isEqualTo(place.getKakaoId().getValue());
        assertThat(registeredPlace.getName().getValue()).isEqualTo(place.getName().getValue());
        assertThat(registeredPlace.getCategory().getGroupCode()).isEqualTo(place.getCategory().getGroupCode());
        assertThat(registeredPlace.getCategory().getGroupName()).isEqualTo(place.getCategory().getGroupName());
        assertThat(registeredPlace.getPhone().getValue()).isEqualTo(place.getPhone().getValue());
        assertThat(registeredPlace.getUrl().getValue()).isEqualTo(place.getUrl().getValue());
        assertThat(registeredPlace.getAddress().getName()).isEqualTo(place.getAddress().getName());
        assertThat(registeredPlace.getAddress().getRoadName()).isEqualTo(place.getAddress().getRoadName());
        assertThat(registeredPlace.getPosition().getLatitude().getValue()).isEqualTo(place.getPosition().getLatitude().getValue());
        assertThat(registeredPlace.getPosition().getLongitude().getValue()).isEqualTo(place.getPosition().getLongitude().getValue());

    }

    @DisplayName("여러 장소를 한번에 저장")
    @Test
    void saveAll() {
        // given
        Place place1 = generatePlace(1, GroupCode.CS2, GroupName.편의점);
        Place place2 = generatePlace(2, GroupCode.AD5, GroupName.숙박);
        Place place3 = generatePlace(3, GroupCode.CT1, GroupName.문화시설);
        List<Place> placeList = List.of(place1, place2, place3);

        // when
        List<Place> savedList = placeService.saveAll(placeList);
        em.clear();

        // then
        assertThat(savedList)
                .hasSize(3)
                .extracting("kakaoId.value", "name.value", "category.groupCode"
                        , "category.groupName", "phone.value", "url.value", "address.name"
                        , "address.roadName.value", "position.longitude.value", "position.latitude.value")
                .containsExactlyInAnyOrder(
                        tuple(place1.getKakaoId().getValue(), place1.getName().getValue(), place1.getCategory().getGroupCode(), place1.getCategory().getGroupName(),
                                place1.getPhone().getValue(), place1.getUrl().getValue(), place1.getAddress().getName(), place1.getAddress().getRoadName().getValue(),
                                place1.getPosition().getLongitude().getValue(), place1.getPosition().getLatitude().getValue()),
                        tuple(place2.getKakaoId().getValue(), place2.getName().getValue(), place2.getCategory().getGroupCode(), place2.getCategory().getGroupName(),
                                place2.getPhone().getValue(), place2.getUrl().getValue(), place2.getAddress().getName(), place2.getAddress().getRoadName().getValue(),
                                place2.getPosition().getLongitude().getValue(), place2.getPosition().getLatitude().getValue()),
                        tuple(place3.getKakaoId().getValue(), place3.getName().getValue(), place3.getCategory().getGroupCode(), place3.getCategory().getGroupName(),
                                place3.getPhone().getValue(), place3.getUrl().getValue(), place3.getAddress().getName(), place3.getAddress().getRoadName().getValue(),
                                place3.getPosition().getLongitude().getValue(), place3.getPosition().getLatitude().getValue())
                );

    }
}