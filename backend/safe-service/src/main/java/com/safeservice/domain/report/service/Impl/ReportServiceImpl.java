package com.safeservice.domain.report.service.Impl;

import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.repository.ReportRepository;
import com.safeservice.domain.report.service.ReportService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    @Transactional
    public Report update(Report report, Long reportId) {
        Report newReport = reportRepository.findById(reportId).get();
        newReport.update(report);
        em.flush();
        return newReport;
    }
}
