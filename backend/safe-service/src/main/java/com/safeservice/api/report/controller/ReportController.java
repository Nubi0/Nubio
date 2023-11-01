package com.safeservice.api.report.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.report.dto.ReportRequestDto;
import com.safeservice.api.report.dto.ReportResponseDto;
import com.safeservice.api.report.dto.ReportUpdateRequestDto;
import com.safeservice.api.report.service.ReportInfoService;
import com.safeservice.global.resolver.identification.Identification;
import com.safeservice.global.resolver.identification.IdentificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Report API", description = "제보 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe")
public class ReportController {

    private final ReportInfoService reportInfoService;

    @Operation(summary = "제보 생성", description = "safe/v1/safe/report\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED")
    })
    @PostMapping(value = "/report",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<String> createReport(@Identification IdentificationDto identificationDto,
                                                  @RequestPart(value = "file", required = false) List<MultipartFile> files,
                                                  @Valid  @RequestPart("report") ReportRequestDto reportRequestDto) {
        reportInfoService.createReport(reportRequestDto, files, identificationDto.getIdentification());
        return ApiResponseEntity.ok("생성 완료");
    }

    @Operation(summary = "제보 수정", description = "safe/v1/safe/report\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @PutMapping("/report")
    public ApiResponseEntity<String> updateReport(@Identification IdentificationDto identificationDto,
                                                  @RequestPart("file") List<MultipartFile> files,
                                                  @Valid @RequestPart("report") ReportUpdateRequestDto reportUpdateRequestDto) {
        reportInfoService.updateReport(reportUpdateRequestDto, files, identificationDto.getIdentification());
        return ApiResponseEntity.ok("수정 완료");
    }

//    @Operation(summary = "현재 위치 기반 제보 조회", description = "safe/v1/safe/report/region\n\n")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK")
//    })
//    @GetMapping("/report/region")
//    public ApiResponseEntity<ReportResponseDto> searchAllByRegion(@Identification IdentificationDto identificationDto,
//                                                          @RequestParam("longitude") double longitude,
//                                                          @RequestParam("latitude") double latitude) {
//        ReportResponseDto responseReport = reportInfoService.searchAllByRegion(
//                identificationDto.getIdentification(),longitude,latitude);
//        return ApiResponseEntity.ok(responseReport);
//    }

    @Operation(summary = "전체 제보 조회", description = "safe/v1/safe/report\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/report")
    public ApiResponseEntity<ReportResponseDto> searchAll(@Identification IdentificationDto identificationDto) {
        ReportResponseDto responseReport = reportInfoService.searchAllByAllow(identificationDto.getIdentification());
        return ApiResponseEntity.ok(responseReport);
    }

    @Operation(summary = "제보 삭제", description = "safe/v1/safe/report/{reportId}\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @DeleteMapping("/report/{reportId}")
    public ApiResponseEntity<String> deleteReport(@Identification IdentificationDto identificationDto,
                                                  @PathVariable("reportId") Long reportId) {
        reportInfoService.deleteReport(identificationDto.getIdentification(),reportId);
        return ApiResponseEntity.ok("삭제 완료");
    }

//    @Operation(summary = "제보 활성화", description = "safe/v1/safe/report/allow/{reportId}\n\n")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK")
//    })
//
//    @GetMapping("/report/allow/{reportId}")
//    public ApiResponseEntity<String> allowReport(@Identification IdentificationDto identificationDto,
//                                                  @PathVariable("reportId") Long reportId) {
//        reportInfoService.allowReport(reportId);
//        return ApiResponseEntity.ok("활성화 완료");
//    }
}
