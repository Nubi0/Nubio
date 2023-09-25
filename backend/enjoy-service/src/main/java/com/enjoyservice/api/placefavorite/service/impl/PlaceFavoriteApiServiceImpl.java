package com.enjoyservice.api.placefavorite.service.impl;

import com.enjoyservice.api.coursefavorite.dto.CourseFavoriteRes;
import com.enjoyservice.api.placefavorite.dto.PlaceFavoriteRes;
import com.enjoyservice.api.placefavorite.service.PlaceFavoriteApiService;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursefavorite.entity.CourseFavorite;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.domain.placefavorite.entity.PlaceFavorite;
import com.enjoyservice.domain.placefavorite.repository.PlaceFavoriteRepository;
import com.enjoyservice.domain.placefavorite.service.service.PlaceFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceFavoriteApiServiceImpl implements PlaceFavoriteApiService {

    private final PlaceFavoriteService placeFavoriteService;
    private final PlaceService placeService;

    @Transactional
    @Override
    public PlaceFavoriteRes placeFavorite(Long placeId, String memberId) {
        Place place = placeService.findById(placeId);
        boolean result = placeFavoriteService.changePlaceFavoriteState(memberId, place);
        return PlaceFavoriteRes.builder()
                .placeId(placeId)
                .placeFavoriteFlag(result)
                .build();
    }
}
