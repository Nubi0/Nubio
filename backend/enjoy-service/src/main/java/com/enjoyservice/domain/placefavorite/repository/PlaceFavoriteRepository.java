package com.enjoyservice.domain.placefavorite.repository;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursefavorite.entity.CourseFavorite;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placefavorite.entity.PlaceFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceFavoriteRepository extends JpaRepository<PlaceFavorite, Long> {

    boolean existsByMemberIdAndPlace(String memberId, Place place);

    Optional<PlaceFavorite> findByMemberIdAndPlace(String memberId, Place place);
}
