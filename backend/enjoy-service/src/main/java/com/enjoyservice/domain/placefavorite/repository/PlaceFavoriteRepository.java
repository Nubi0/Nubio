package com.enjoyservice.domain.placefavorite.repository;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placefavorite.entity.PlaceFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceFavoriteRepository extends JpaRepository<PlaceFavorite, Long> {

    boolean existsByMemberIdAndPlace(String memberId, Place place);
}
