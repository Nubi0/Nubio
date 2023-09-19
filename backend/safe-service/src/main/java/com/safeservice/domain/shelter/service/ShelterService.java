package com.safeservice.domain.shelter.service;

import com.safeservice.domain.shelter.constant.ShelterType;
import com.safeservice.domain.shelter.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import java.util.List;
public interface ShelterService {

    Shelter register(Shelter shelter);

    List<Shelter> findShelterNear(Point point, Distance distance, ShelterType shelterType);

    Page<Shelter> findShelterNearWithPaging(Point point, Distance distance, ShelterType shelterType, Pageable pageable);

    List<Shelter> saveAll(List<Shelter> shelterList);

    List<Shelter> findShelterNear(Point point, Distance distance);
    Page<Shelter> findShelterNearWithPaging(Point point, Distance distance,Pageable pageable);
}
