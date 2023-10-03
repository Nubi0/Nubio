package com.safeservice.domain.report.service;

import com.safeservice.domain.report.entity.Report;

import java.util.List;

public interface ReportService {

    Report save(Report report);
    Report update(Report report, Long reportId, String identification);

    void delete(String identification, Long id);
    List<Report> searchReport(double longitude, double latitude);
    List<Report> searchAllReport();
}
