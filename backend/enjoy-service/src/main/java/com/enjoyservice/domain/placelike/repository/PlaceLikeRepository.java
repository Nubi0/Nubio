package com.enjoyservice.domain.placelike.repository;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceLikeRepository extends JpaRepository<PlaceLike, Long> {

    @Query("select count(pl) from PlaceLike pl where pl.place = :place")
    int getCountByPlace(@Param("place") Place place);

    List<PlaceLike> findAllByPlace(Place place);

    void deleteByMemberIdAndPlace(String memberId, Place place);
}
