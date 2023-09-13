package com.enjoyservice.domain.place.service.impl;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.exception.PlaceNotFoundException;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Override
    public Place findById(long id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(ErrorCode.PLACE_NOT_FOUND));
    }
}
