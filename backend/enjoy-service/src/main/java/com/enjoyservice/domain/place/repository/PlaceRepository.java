package com.enjoyservice.domain.place.repository;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.type.KakaoId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select p from Place p left join fetch p.images where p.id = :id")
    List<Place> findOneByIdFetchImage(@Param("id") Long id, Pageable pageable);

    @Query("select p from Place p where p.kakaoId in :kakaoIds")
    List<Place> findAllByKakaoIds(@Param("kakaoIds") List<KakaoId> kakaoIds);
}
