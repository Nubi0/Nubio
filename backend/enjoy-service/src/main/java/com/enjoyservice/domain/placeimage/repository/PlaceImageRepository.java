package com.enjoyservice.domain.placeimage.repository;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placeimage.entity.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PlaceImageRepository extends JpaRepository<PlaceImage, Long> {
}
