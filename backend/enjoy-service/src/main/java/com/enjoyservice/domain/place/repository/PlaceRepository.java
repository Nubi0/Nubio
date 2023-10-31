package com.enjoyservice.domain.place.repository;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.type.KakaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select p from Place p left join fetch p.images where p.id = :id")
    List<Place> findOneByIdFetchImage(@Param("id") Long id, Pageable pageable);

    @Query("select p from Place p where p.kakaoId in :kakaoIds")
    List<Place> findAllByKakaoIds(@Param("kakaoIds") List<KakaoId> kakaoIds);

    @Query(value = "SELECT * FROM place WHERE ST_Distance_Sphere(POINT(:lng, :lat), POINT(longitude, latitude )) <= :dist",
            nativeQuery = true)
    Page<Place> findNearPlace(@Param("lng") double lng, @Param("lat") double lat, @Param("dist") int dist, Pageable pageable);

    @Query(value = "SELECT * FROM place WHERE ST_Distance_Sphere(POINT(:lng, :lat), POINT(longitude, latitude )) <= :dist " +
            "AND category_group_code = :category",
            nativeQuery = true)
    Page<Place> findNearPlaceType(@Param("lng") double lng, @Param("lat") double lat, @Param("dist") int dist, @Param("category") String category, Pageable pageable);

    @Query(value = "SELECT * FROM place WHERE ST_Distance_Sphere(POINT(:lng, :lat), POINT(longitude, latitude )) <= :dist " +
            "AND name Like %:name%",
            nativeQuery = true)
    Page<Place> searchNearPlace(@Param("lng") double lng, @Param("lat") double lat, @Param("dist") int dist, @Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM place WHERE ST_Distance_Sphere(POINT(:lng, :lat), POINT(longitude, latitude )) <= :dist " +
            "AND category_group_code = :category " +
            "AND name Like %:name% ",
            nativeQuery = true)
    Page<Place> searchNearPlaceByTypeAndName(@Param("lng") double lng, @Param("lat") double lat, @Param("dist") int dist, @Param("category") String category, @Param("name") String name, Pageable pageable);

    Optional<Place> findByKakaoId(KakaoId kakaoId);

    boolean existsByKakaoId(KakaoId kakaoId);

}
