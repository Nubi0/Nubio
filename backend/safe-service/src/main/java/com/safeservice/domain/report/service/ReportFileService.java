package com.safeservice.domain.report.service;

import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.ReportFile;

public interface ReportFileService {
    ReportFile saveAccuseFile(String fileName, String url, Report report);
}
