package com.enjoyservice.api.placelike.service.impl;

import com.enjoyservice.api.placelike.dto.PlaceLikeRes;
import com.enjoyservice.api.placelike.service.PlaceLikeApiService;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import com.enjoyservice.domain.placelike.service.PlaceLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceLikeApiServiceImpl implements PlaceLikeApiService {

    private final PlaceLikeService placeLikeService;
    private final PlaceService placeService;

    @Transactional
    @Override
    public PlaceLikeRes likePlace(String memberId, Long placeId) {
        Place place = placeService.findById(placeId);
        boolean result = placeLikeService.changePlaceLikeState(memberId, place);
        List<PlaceLike> placeLikes = placeLikeService.findAllByPlaceAndActiveIsTrue(place);
        long likeCount = placeLikes.size();
        return PlaceLikeRes.builder()
                .likeCount(likeCount)
                .likeFlag(result)
                .build();
    }
}
