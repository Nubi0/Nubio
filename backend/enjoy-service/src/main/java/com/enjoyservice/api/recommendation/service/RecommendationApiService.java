package com.enjoyservice.api.recommendation.service;

import com.enjoyservice.api.recommendation.dto.RecommendationReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoRes;

public interface RecommendationApiService {
    void saveModel(String region);
    FastRecoRes getCourses(String memberId ,RecommendationReq recommendationReq);

}
