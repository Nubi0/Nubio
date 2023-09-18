package com.enjoyservice.api.place.service;

import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.domain.placefavorite.service.service.PlaceFavoriteService;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import com.enjoyservice.domain.placelike.service.PlaceLikeService;
import com.enjoyservice.mapper.place.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceApiService {

    private final PlaceService placeService;
    private final PlaceLikeService placeLikeService;
    private final PlaceFavoriteService placeFavoriteService;

    public PlaceInfoRes getPlaceInfo(String memberId, long placeId) {
        Place place = placeService.findOneByIdFetchImage(placeId).get(0);
        List<PlaceLike> placeLikeResults = placeLikeService.findAllByPlaceAndActiveIsTrue(place);
        int likeCount = placeLikeResults.size();
        boolean likeFlag = isMemberLikePlace(memberId, placeLikeResults);
        boolean favoriteFlag = placeFavoriteService.existsByMemberIdAndPlace(memberId, place);

        return PlaceMapper.toPlaceInfoRes(place, likeCount, likeFlag, favoriteFlag);
    }

    private boolean isPresentOnePlace(List<Place> places) {
        return places.size() == 1;
    }

    private boolean isMemberLikePlace(String memberId, List<PlaceLike> likes) {
        return likes.stream()
                .anyMatch(placeLike -> placeLike.getMemberId().equals(memberId));
    }
}
