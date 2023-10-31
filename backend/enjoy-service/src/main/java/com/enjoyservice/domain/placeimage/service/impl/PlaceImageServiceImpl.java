package com.enjoyservice.domain.placeimage.service.impl;

import com.enjoyservice.domain.placeimage.entity.PlaceImage;
import com.enjoyservice.domain.placeimage.repository.PlaceImageRepository;
import com.enjoyservice.domain.placeimage.service.PlaceImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PlaceImageServiceImpl implements PlaceImageService {

    private final PlaceImageRepository placeImageRepository;

    @Override
    public List<PlaceImage> saveAll(List<PlaceImage> placeImages) {
        return placeImageRepository.saveAllAndFlush(placeImages);
    }
}
