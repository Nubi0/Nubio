package com.enjoyservice.api.placelike.service;

import com.enjoyservice.api.placelike.dto.PlaceLikeRes;

public interface PlaceLikeApiService {

    public PlaceLikeRes likePlace(String memberId, Long placeId);
}
