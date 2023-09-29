package com.enjoyservice.mapper.place;

import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.domain.place.entity.Place;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlaceMapper {

    public static PlaceInfoRes toPlaceInfoRes(Place place, int likeCount, boolean likeFlag, boolean favoriteFlag) {

        log.info("여기서 이미지 조회 쿼리 날라가나?");
        String imgUrl = place.getImages().stream().map(image -> image.getUrl().getValue()).findFirst().isPresent()
                ? place.getImages().stream().map(image -> image.getUrl().getValue()).findFirst().get() : "not exist";

        return PlaceInfoRes.builder()
                .placeInfo(PlaceInfoRes.PlaceInfo.builder()
                        .addressName(place.getAddress().getName())
                        .categoryGroupCode(place.getCategory().getGroupCode().toString())
                        .categoryGroupName(place.getCategory().getGroupName().toString())
                        .categoryName(place.getCategory().getDetail().getValue())
                        .id(place.getKakaoId().getValue().toString())
                        .phone(place.getPhone().getValue())
                        .placeName(place.getName().getValue())
                        .placeUrl(place.getUrl().getValue())
                        .roadAddressName(place.getAddress().getRoadName().getValue())
                        .longitude(place.getPosition().getLongitude().getValue())
                        .latitude(place.getPosition().getLatitude().getValue())
                        .imgUrl(imgUrl)
                        .build())
                .meta(PlaceInfoRes.Meta.builder()
                        .likeCount(likeCount)
                        .likeFlag(likeFlag)
                        .favoriteFlag(favoriteFlag)
                        .build())
                .build();
    }

}
