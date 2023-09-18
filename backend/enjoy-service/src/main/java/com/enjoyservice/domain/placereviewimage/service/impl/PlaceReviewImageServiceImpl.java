package com.enjoyservice.domain.placereviewimage.service.impl;

import com.enjoyservice.domain.placereviewimage.entity.PlaceReviewImage;
import com.enjoyservice.domain.placereviewimage.repository.PlaceReviewImageRepository;
import com.enjoyservice.domain.placereviewimage.service.PlaceReviewImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceReviewImageServiceImpl implements PlaceReviewImageService {

    private final PlaceReviewImageRepository placeReviewImageRepository;

    @Override
    public void save(PlaceReviewImage placeReviewImage) {
        placeReviewImageRepository.save(placeReviewImage);
    }
}
