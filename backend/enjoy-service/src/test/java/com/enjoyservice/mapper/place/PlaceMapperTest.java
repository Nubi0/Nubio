package com.enjoyservice.mapper.place;

import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.placeimage.entity.PlaceImage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class PlaceMapperTest {

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

    @DisplayName("PlaceInfoRes :  Place에 imgUrl 없는 dto 만들기")
    @Test
    void toPlaceInfoResNoImg() {
        // given
        Place place = generatePlace(0, GroupCode.CE7, GroupName.카페);
        int likeCount = 10;
        boolean likeFlag = true;
        boolean favoriteFlag = true;
        // when
        PlaceInfoRes placeInfoRes = PlaceMapper.toPlaceInfoRes(place, likeCount, likeFlag, favoriteFlag);
        // then
        assertThat(placeInfoRes.getPlaceInfo().getAddressName()).isEqualTo(place.getAddress().getName());
        assertThat(placeInfoRes.getPlaceInfo().getCategoryGroupCode()).isEqualTo(place.getCategory().getGroupCode().toString());
        assertThat(placeInfoRes.getPlaceInfo().getImgUrl()).isEqualTo("not exist");
        // TODO: 나중에 필드 다 해야됨
        assertThat(placeInfoRes.getMeta().getLikeCount()).isEqualTo(likeCount);
        assertThat(placeInfoRes.getMeta().isLikeFlag()).isTrue();
        assertThat(placeInfoRes.getMeta().isFavoriteFlag()).isTrue();
    }

    @DisplayName("PlaceInfoRes :  Place에 imgUrl 있는 dto 만들기")
    @Test
    void toPlaceInfoRes() {
        // given
        Place place = generatePlace(0, GroupCode.CE7, GroupName.카페);
        PlaceImage placeImage = PlaceImage.builder().place(place)
                .url(com.enjoyservice.domain.placeimage.entity.type.Url.from("url"))
                .build();
        place.getImages().add(placeImage);
        int likeCount = 10;
        boolean likeFlag = true;
        boolean favoriteFlag = true;
        // when
        PlaceInfoRes placeInfoRes = PlaceMapper.toPlaceInfoRes(place, likeCount, likeFlag, favoriteFlag);
        // then
        assertThat(placeInfoRes.getPlaceInfo().getAddressName()).isEqualTo(place.getAddress().getName());
        assertThat(placeInfoRes.getPlaceInfo().getCategoryGroupCode()).isEqualTo(place.getCategory().getGroupCode().toString());
        assertThat(placeInfoRes.getPlaceInfo().getImgUrl()).isEqualTo("url");
        // TODO: 나중에 필드 다 해야됨
        assertThat(placeInfoRes.getMeta().getLikeCount()).isEqualTo(likeCount);
        assertThat(placeInfoRes.getMeta().isLikeFlag()).isTrue();
        assertThat(placeInfoRes.getMeta().isFavoriteFlag()).isTrue();
    }
}