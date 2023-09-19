package com.safeservice.domain.shelter.service.impl;

import com.safeservice.domain.shelter.constant.ShelterType;
import com.safeservice.domain.shelter.entity.Shelter;
import com.safeservice.domain.shelter.mongo.ShelterRepository;
import com.safeservice.domain.shelter.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    @Override
    @Transactional
    public Shelter register(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    @Override
    public List<Shelter> findShelterNear(Point point, Distance distance, ShelterType shelterType) {
        return shelterRepository.findByLocationNearAndShelterTypeIs(point, distance, shelterType);
    }

    @Override
    public Page<Shelter> findShelterNearWithPaging(Point point, Distance distance, ShelterType shelterType, Pageable pageable) {
        return shelterRepository.findByLocationNearAndShelterTypeIs(point, distance, shelterType, pageable);
    }

    @Override
    public List<Shelter> saveAll(List<Shelter> shelterList) {
        return shelterRepository.saveAll(shelterList);
    }

    @Override
    public List<Shelter> findShelterNear(Point point, Distance distance) {
        return shelterRepository.findByLocationNear(point, distance);
    }

    @Override
    public Page<Shelter> findShelterNearWithPaging(Point point, Distance distance, Pageable pageable) {
        return shelterRepository.findByLocationNear(point, distance, pageable);
    }
}
