package com.safeservice.domain.report.service.Impl;

import com.safeservice.domain.report.entity.Report;
import com.safeservice.domain.report.entity.ReportFile;
import com.safeservice.domain.report.entity.type.reportfile.Active;
import com.safeservice.domain.report.entity.type.reportfile.FileName;
import com.safeservice.domain.report.entity.type.reportfile.FileUrl;
import com.safeservice.domain.report.repository.ReportFileRepository;
import com.safeservice.domain.report.service.ReportFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportFileServiceImpl implements ReportFileService {

    private final ReportFileRepository reportFileRepository;

    @Override
    @Transactional
    public ReportFile saveAccuseFile(String fileName, String url, Report report) {
        ReportFile accuseFile = ReportFile.builder()
                .fileName(FileName.from(fileName))
                .fileUrl(FileUrl.from(url))
                .report(report)
                .active(Active.from(true))
                .build();
        return reportFileRepository.save(accuseFile);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ReportFile reportFile = reportFileRepository.findById(id).get();
        reportFile.updateActive();
    }
}
