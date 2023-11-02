package com.safeservice.domain.report.repository;

import com.safeservice.domain.report.entity.Report;

import java.util.List;

public interface ReportRepositoryCustom {

    Report searchAllReport(double longitude, double latitude, String region);
    List<Report> searchAll();
}
