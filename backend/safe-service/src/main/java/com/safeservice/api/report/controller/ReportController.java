package com.safeservice.api.report.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.report.dto.ReportRequestDto;
import com.safeservice.api.report.service.ReportInfoService;
import com.safeservice.global.resolver.identification.Identification;
import com.safeservice.global.resolver.identification.IdentificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class ReportController {

    private final ReportInfoService reportInfoService;

    @PostMapping("/report")
    public ApiResponse<String> createReport(@Identification IdentificationDto identificationDto,
                                            @RequestPart("file") List<MultipartFile> files,
                                            @RequestPart String title,
                                            @RequestPart String content,
                                            @RequestPart String reportType) {
        reportInfoService.createReport(ReportRequestDto.of(
                title,content,reportType), files, identificationDto.getIdentification());
        return ApiResponse.ok("생성 완료");
    }
}
