package com.enjoyservice.domain.place.repository;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.placeimage.entity.PlaceImage;
import com.enjoyservice.domain.placeimage.repository.PlaceImageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PlaceRepositoryTest {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceImageRepository placeImageRepository;

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

    @DisplayName("Place 조회 시 Place와 연관된 PlaceImage 중 1개 즉시 로딩(이미지가 1개 이상 있을 때)")
    @Test
    void findByIdWithImage() {
        // given
        Place targetPlace = generatePlace(0, GroupCode.CD7, GroupName.카페);
        Place savedTargetPlace = placeRepository.saveAndFlush(targetPlace);
        Place placeFood = generatePlace(1, GroupCode.FD6, GroupName.음식점);
        Place savedPlaceFood = placeRepository.saveAndFlush(placeFood);

        final int imageCount = 3;
        List<PlaceImage> beforeImages = new ArrayList<>();
        for(int imageIndex = 0; imageIndex < imageCount; imageIndex++) {
            String name = "imageName" + imageIndex;
            String url = "imageUrl" + imageIndex;

            PlaceImage placeImage = PlaceImage.builder()
                    .name(com.enjoyservice.domain.placeimage.entity.type.Name.from(name))
                    .url(com.enjoyservice.domain.placeimage.entity.type.Url.from(url))
                    .place(savedTargetPlace)
                    .build();

            PlaceImage savedPlaceImage = placeImageRepository.saveAndFlush(placeImage);
            beforeImages.add(savedPlaceImage);
        }
        // when
        List<Place> result = placeRepository.findOneByIdFetchImage(targetPlace.getId(), PageRequest.of(0, 1));
        // then
        assertThat(result).hasSize(1);
    }

    @DisplayName("Place 조회 시 Place와 연관된 PlaceImage 중 1개 즉시 로딩(이미지가 없을 때)")
    @Test
    void findByIdWithNoImage() {
        // given
        Place targetPlace = generatePlace(0, GroupCode.CD7, GroupName.카페);
        Place savedTargetPlace = placeRepository.saveAndFlush(targetPlace);
        Place placeFood = generatePlace(1, GroupCode.FD6, GroupName.음식점);
        Place savedPlaceFood = placeRepository.saveAndFlush(placeFood);

        final int imageCount = 3;
        List<PlaceImage> beforeImages = new ArrayList<>();
        for(int imageIndex = 0; imageIndex < imageCount; imageIndex++) {
            String name = "imageName" + imageIndex;
            String url = "imageUrl" + imageIndex;

            PlaceImage placeImage = PlaceImage.builder()
                    .name(com.enjoyservice.domain.placeimage.entity.type.Name.from(name))
                    .url(com.enjoyservice.domain.placeimage.entity.type.Url.from(url))
                    .place(savedPlaceFood)
                    .build();

            PlaceImage savedPlaceImage = placeImageRepository.saveAndFlush(placeImage);
            beforeImages.add(savedPlaceImage);
        }
        // when
        List<Place> result = placeRepository.findOneByIdFetchImage(savedTargetPlace.getId(), PageRequest.of(0, 1));
        // then
        assertThat(result).hasSize(0);
    }
}