package com.enjoyservice.domain.placelike.service;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placelike.entity.PlaceLike;

import java.util.List;

public interface PlaceLikeService {

    List<PlaceLike> findAllByPlaceAndActiveIsTrue(Place place);

    boolean changePlaceLikeState(String memberId, Place place);
}
