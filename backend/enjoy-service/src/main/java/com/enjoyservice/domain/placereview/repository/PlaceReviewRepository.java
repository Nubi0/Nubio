package com.enjoyservice.domain.placereview.repository;

import com.enjoyservice.domain.placereview.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long> {
}
