package com.safeservice.api.report.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.safeservice.api.report.dto.*;
import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.service.ReportFileService;
import com.safeservice.domain.report.service.ReportService;
import com.safeservice.global.error.ErrorCode;
import com.safeservice.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import com.safeservice.global.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportInfoService {

    private final ReportService reportService;
    private final ReportFileService reportFileService;
    private final AmazonS3Client amazonS3Client;

    private static final int FILE_AMOUNT_LIMIT = 5;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Transactional
    public void createReport(ReportRequestDto reportRequestDto, List<MultipartFile> files, String identification) {

        Report report = ReportRequestDto.toEntity(reportRequestDto, identification);
        Report savedReport = reportService.save(report);
        uploadIPFiles("report", files, savedReport);
    }

    @Transactional
    public void updateReport(ReportUpdateRequestDto reportUpdateRequestDto, List<MultipartFile> files, String identification) {
        Report report = ReportUpdateRequestDto.toEntity(reportUpdateRequestDto);
        Report savedReport = reportService.update(report,reportUpdateRequestDto.getReportId(),identification);
        uploadIPFiles("report", files, savedReport);
    }

    public ReportResponseDto searchAll(String identification) {
        List<Report> reports = reportService.searchReport();
        List<ReportListDto> reportList = new ArrayList<>();
        for (Report report : reports) {
            reportList.add(ReportListDto.of(report, identification));
        }
        return ReportResponseDto.from(reportList);
    }

    @Transactional
    public List<ReportFileDto> uploadIPFiles(String category,
                                             List<MultipartFile> files,
                                             Report report) {

        List<MultipartFile> validMultipartFiles = files.stream()
                .filter(this::validateFileExists)
                .collect(Collectors.toList());

        validateFileSize(validMultipartFiles);

        LocalDateTime now = LocalDateTime.now();

        List<ReportFileDto> responseDtos = new ArrayList<>();
        for (MultipartFile file : validMultipartFiles) {
            String fileName = FileUtils.buildFileName(category, report.getId(), file.getOriginalFilename(), now);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                String url = amazonS3Client.getUrl(bucketName, fileName).toString();
                ReportFileDto fileUploadResponseDto = new ReportFileDto(url);
                responseDtos.add(fileUploadResponseDto);

                reportFileService.saveAccuseFile(file.getOriginalFilename(), url, objectMetadata.getContentLength(), report);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return responseDtos;
    }

    private boolean validateFileExists(MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.EMPTY_FILE);
        }
        return true;
    }

    private void validateFileSize(List<MultipartFile> files) {
        if (files.size() > FILE_AMOUNT_LIMIT) {
            throw new BusinessException(ErrorCode.FILE_AMOUNTS_LIMIT);
        }
    }
}
