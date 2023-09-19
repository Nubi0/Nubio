package com.safeservice.domain.shelter.mongo;

import com.safeservice.domain.shelter.constant.ShelterType;
import com.safeservice.domain.shelter.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends MongoRepository<Shelter, String> {

    List<Shelter> findByLocationNearAndShelterTypeIs(Point point, Distance distance, ShelterType shelterType);

    Page<Shelter> findByLocationNearAndShelterTypeIs(Point point, Distance distance, ShelterType shelterType, Pageable pageable);



}
