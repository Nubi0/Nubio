package com.enjoyservice.global.config.jpa;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public Optional<String> getCurrentAuditor() {
        String modifiedBy = httpServletRequest.getHeader("Identification");
        // 수정 경로가 없으면 "unknown" 으로 설정
        if (!StringUtils.hasText(modifiedBy)) {
            modifiedBy = "unknown";
        }
        return Optional.of(modifiedBy);
    }
}
