package com.safeservice.api.safetybell.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.safetybell.service.SafetyBellInfoService;
import com.safeservice.domain.safetybell.entity.SafetyBell;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class SafeBellController {

    private final SafetyBellInfoService safetyBellInfoService;

    @PostMapping("/safety-bell/upload-csv-file")
    public ApiResponse<String> createReport(@RequestPart("file") MultipartFile file) {
        safetyBellInfoService.register(file);
        return ApiResponse.ok("저장완료");
    }

}
