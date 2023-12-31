package com.safeservice.api.admin.controller;

import com.safeservice.api.ApiResponseEntity;
import com.safeservice.api.report.dto.ReportResponseDto;
import com.safeservice.api.report.service.ReportInfoService;
import com.safeservice.global.resolver.identification.Identification;
import com.safeservice.global.resolver.identification.IdentificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ADMIN API", description = "관리자 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/safe/admin")
public class AdminController {

    private final ReportInfoService reportInfoService;

    @Operation(summary = "전체 제보 조회", description = "safe/v1/safe/admin/report\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/report")
    public ApiResponseEntity<ReportResponseDto> searchAll(@Identification IdentificationDto identificationDto) {
        ReportResponseDto responseReport = reportInfoService.searchAll(identificationDto.getIdentification());
        return ApiResponseEntity.ok(responseReport);
    }

    @Operation(summary = "관리자 제보 삭제", description = "safe/v1/safe/admin/report/{reportId}\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping("/delete/{reportId}")
    public ApiResponseEntity<String> deleteReport(@Identification IdentificationDto identificationDto,
                                                          @PathVariable("reportId") Long reportId) {
        reportInfoService.adminDeleteReport(reportId);
        return ApiResponseEntity.ok("삭제 완료");
    }
}
