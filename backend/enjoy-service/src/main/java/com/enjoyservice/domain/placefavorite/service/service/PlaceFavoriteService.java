package com.enjoyservice.domain.placefavorite.service.service;

import com.enjoyservice.domain.place.entity.Place;

public interface PlaceFavoriteService {

    boolean existsByMemberIdAndPlace(String memberId, Place place);
}
