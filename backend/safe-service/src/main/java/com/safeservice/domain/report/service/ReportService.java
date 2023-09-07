package com.safeservice.domain.report.service;

import com.safeservice.domain.report.entity.Report;

public interface ReportService {

    Report save(Report report);
    Report update(Report report, Long reportId);

    void delete(Long id);
}
