package com.enjoyservice.domain.placefavorite.service.service.impl;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placefavorite.repository.PlaceFavoriteRepository;
import com.enjoyservice.domain.placefavorite.service.service.PlaceFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceFavoriteServiceImpl implements PlaceFavoriteService {

    private final PlaceFavoriteRepository placeFavoriteRepository;

    @Override
    public boolean existsByMemberIdAndPlace(String memberId, Place place) {
        return placeFavoriteRepository.existsByMemberIdAndPlace(memberId, place);
    }
}
