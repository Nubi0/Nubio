package com.enjoyservice.domain.place.service.impl;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.type.KakaoId;
import com.enjoyservice.domain.place.exception.PlaceNotFoundException;
import com.enjoyservice.domain.place.repository.PlaceRepository;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

    @Override
    public List<Place> findAllById(List<Long> ids) {
        return placeRepository.findAllById(ids);
    }

    @Override
    public List<Place> findAllByKakaoId(List<KakaoId> kakaoIds) {
        return placeRepository.findAllByKakaoIds(kakaoIds);
    }

    @Override
    public Place findOneByIdFetchImage(Long id) {
        Page<Place> result = placeRepository.findOneByIdFetchImage(id, PageRequest.of(0, 1));
        List<Place> list = result.getContent();
        log.info("list: {}, list-size: {}", list, list.size());
        return list.get(0);
    }

    @Override
    @Transactional
    public List<Place> saveAll(List<Place> placeList) {
        return placeRepository.saveAllAndFlush(placeList);
    }

    @Override
    @Transactional
    public Place register(Place place) {
        return placeRepository.saveAndFlush(place);
    }
}
