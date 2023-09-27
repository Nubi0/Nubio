package com.enjoyservice.domain.place.service;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.type.KakaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceService {

    Place findById(long id);

    List<Place> findAllById(List<Long> ids);

    List<Place> findAllByKakaoId(List<KakaoId> kakaoIds);

    Place findOneByIdFetchImage(Long id);

    List<Place> saveAll(List<Place> placeList);

    Place register(Place place);

    Page<Place> findNearPlace(double lng, double lat, int dist, Pageable pageable);

    Page<Place> findNearPlaceType(double lng, double lat, int dist, String category, Pageable pageable);

    Page<Place> searchNearPlace(double lng, double lat, int dist, String name, Pageable pageable);

    Page<Place> searchNearPlaceByTypeAndName(double lng, double lat, int dist, String category, String name, Pageable pageable);
}
