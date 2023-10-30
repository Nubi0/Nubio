package com.enjoyservice.domain.placeimage.service;

import com.enjoyservice.domain.placeimage.entity.PlaceImage;

import java.util.List;

public interface PlaceImageService {
    List<PlaceImage> saveAll(List<PlaceImage> placeImages);
}
