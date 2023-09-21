package com.enjoyservice.api.place.controller;

import com.enjoyservice.api.ApiResponse;
import com.enjoyservice.api.place.service.PlaceApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/enjoy/place")
@RequiredArgsConstructor
public class PlaceFileController {

    private final PlaceApiService placeApiService;

    @PostMapping("/upload-csv-file")
    public ApiResponse<String> saveNode(@RequestPart("file") MultipartFile file) {
        placeApiService.placeCsvToDb(file);
        return ApiResponse.ok("저장완료");
    }

}
