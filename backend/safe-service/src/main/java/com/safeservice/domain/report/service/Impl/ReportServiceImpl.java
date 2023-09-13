package com.safeservice.domain.report.service.Impl;

import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.exception.MisMatchIdentification;
import com.safeservice.domain.report.repository.ReportRepository;
import com.safeservice.domain.report.service.ReportService;
import com.safeservice.global.error.ErrorCode;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Report update(Report report, Long reportId, String identification) {
        Report newReport = reportRepository.findById(reportId).get();
        validateIdentification(identification, newReport);
        newReport.update(report);
        em.flush();
        return newReport;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Report report = reportRepository.findById(id).get();
        report.updateActive();
    }

    private void validateIdentification(String identification, Report report) {
        if (!report.getIdentification().equals(identification)) {
            throw new MisMatchIdentification(ErrorCode.MISMATCH_IDENTIFICATION);
        }
    }

    @Override
    public List<Report> searchReport() {
        return reportRepository.searchAllReport();
    }

}
