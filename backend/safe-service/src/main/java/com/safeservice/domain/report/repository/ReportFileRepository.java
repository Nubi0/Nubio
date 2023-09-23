package com.safeservice.domain.report.repository;

import com.safeservice.domain.report.entity.ReportFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportFileRepository extends JpaRepository<ReportFile,Long> {
}
