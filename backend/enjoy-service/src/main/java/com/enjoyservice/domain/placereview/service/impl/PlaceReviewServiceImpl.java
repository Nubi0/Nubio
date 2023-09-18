package com.enjoyservice.domain.placereview.service.impl;

import com.enjoyservice.domain.placereview.entity.PlaceReview;
import com.enjoyservice.domain.placereview.repository.PlaceReviewRepository;
import com.enjoyservice.domain.placereview.service.PlaceReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceReviewServiceImpl implements PlaceReviewService {

    private final PlaceReviewRepository placeReviewRepository;

    @Override
    public void save(PlaceReview placeReview) {
        placeReviewRepository.save(placeReview);
    }
}
