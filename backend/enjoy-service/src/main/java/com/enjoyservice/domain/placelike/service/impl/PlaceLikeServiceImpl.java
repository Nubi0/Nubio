package com.enjoyservice.domain.placelike.service.impl;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import com.enjoyservice.domain.placelike.repository.PlaceLikeRepository;
import com.enjoyservice.domain.placelike.service.PlaceLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceLikeServiceImpl implements PlaceLikeService {

    private final PlaceLikeRepository placeLikeRepository;

    @Override
    public List<PlaceLike> findAllByPlace(Place place) {
        return placeLikeRepository.findAllByPlace(place);
    }
}
