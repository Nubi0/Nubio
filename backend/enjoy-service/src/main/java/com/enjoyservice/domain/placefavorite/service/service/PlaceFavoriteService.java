package com.enjoyservice.domain.placefavorite.service.service;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placefavorite.entity.PlaceFavorite;

import java.util.Optional;

public interface PlaceFavoriteService {

    boolean existsByMemberIdAndPlace(String memberId, Place place);

    boolean changePlaceFavoriteState(String memberId, Place place);
}
