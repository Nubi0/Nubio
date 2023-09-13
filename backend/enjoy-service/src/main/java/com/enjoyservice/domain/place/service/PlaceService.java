package com.enjoyservice.domain.place.service;

import com.enjoyservice.domain.place.entity.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceService {

    Place findById(long id);

    List<Place> findOneByIdFetchImage(Long id);
}
