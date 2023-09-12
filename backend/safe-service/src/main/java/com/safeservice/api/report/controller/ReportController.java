package com.safeservice.api.report.controller;

import com.safeservice.api.ApiResponse;
import com.safeservice.api.report.dto.ReportRequestDto;
import com.safeservice.api.report.dto.ReportListDto;
import com.safeservice.api.report.dto.ReportResponseDto;
import com.safeservice.api.report.dto.ReportUpdateRequestDto;
import com.safeservice.api.report.service.ReportInfoService;
import com.safeservice.global.resolver.identification.Identification;
import com.safeservice.global.resolver.identification.IdentificationDto;
import jakarta.validation.Valid;
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
                                            @RequestPart(value = "file", required = false) List<MultipartFile> files,
                                            @Valid  @RequestPart("report") ReportRequestDto reportRequestDto) {
        reportInfoService.createReport(reportRequestDto, files, identificationDto.getIdentification());
        return ApiResponse.ok("생성 완료");
    }

    @PutMapping("/report")
    public ApiResponse<String> updateReport(@Identification IdentificationDto identificationDto,
                                            @RequestPart("file") List<MultipartFile> files,
                                            @Valid @RequestPart("report") ReportUpdateRequestDto reportUpdateRequestDto) {
        reportInfoService.updateReport(reportUpdateRequestDto, files, identificationDto.getIdentification());
        return ApiResponse.ok("수정 완료");
    }

    @GetMapping("/report")
    public ApiResponse<ReportResponseDto> searchAll(@Identification IdentificationDto identificationDto) {
        ReportResponseDto responseReport = reportInfoService.searchAll(identificationDto.getIdentification());
        return ApiResponse.ok(responseReport);
    }

}
