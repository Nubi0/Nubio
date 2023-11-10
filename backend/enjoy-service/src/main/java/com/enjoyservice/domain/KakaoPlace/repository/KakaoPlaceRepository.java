package com.enjoyservice.domain.KakaoPlace.repository;

import com.enjoyservice.domain.KakaoPlace.entity.KakaoPlace;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface KakaoPlaceRepository extends ElasticsearchRepository<KakaoPlace, String> {

}
