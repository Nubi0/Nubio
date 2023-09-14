package com.enjoyservice.mapper.place;

import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.domain.place.entity.Place;

public class PlaceMapper {

    public static PlaceInfoRes toPlaceInfoRes(Place place, int likeCount, boolean likeFlag, boolean favoriteFlag) {

        String imgUrl = "";
        int imageSize = place.getImages().size();
        if(imageSize >= 1) {
            imgUrl = place.getImages().get(0).getUrl().getValue();
        } else {
            imgUrl = "dummy_img"; // TODO: 나중에 더미 이미지 url 넣기
        }

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
