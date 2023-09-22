package com.enjoyservice.api.placefavorite.service;

import com.enjoyservice.api.coursefavorite.dto.CourseFavoriteRes;
import com.enjoyservice.api.placefavorite.dto.PlaceFavoriteRes;
import com.enjoyservice.domain.place.entity.Place;

public interface PlaceFavoriteApiService {

    PlaceFavoriteRes placeFavorite(Long placeId, String memberId);
}
