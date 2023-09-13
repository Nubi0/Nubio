package com.enjoyservice.domain.place.repository;

import com.enjoyservice.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
