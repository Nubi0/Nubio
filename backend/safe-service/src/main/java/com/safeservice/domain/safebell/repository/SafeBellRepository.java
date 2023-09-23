package com.safeservice.domain.safebell.repository;

import com.safeservice.domain.safebell.dto.response.NearestSafeBellDto;
import com.safeservice.domain.safebell.entity.SafeBell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SafeBellRepository extends JpaRepository<SafeBell, Long> {

    @Query(value = "SELECT * FROM safe_bell WHERE ST_Distance_Sphere(POINT(:lng, :lat), POINT(longitude, latitude )) <= :dist",
            nativeQuery = true)
    List<SafeBell> findWithinDistance(@Param("lng") Double lng, @Param("lat") Double lat, @Param("dist") Integer dist);

    @Query(nativeQuery = true, name = "findNearestSafeBell")
    Optional<NearestSafeBellDto> findNearestSafeBell(@Param("lng") double longitude, @Param("lat") double latitude);
}
