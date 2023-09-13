package com.enjoyservice.domain.place.repository;

import com.enjoyservice.domain.place.entity.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select p, pi from Place p inner join fetch PlaceImage pi on p = pi.place where p.id = :id")
    List<Place> findOneByIdFetchImage(@Param("id") Long id, Pageable pageable);
}
