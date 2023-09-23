package com.enjoyservice.global.config.jpa;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final HttpServletRequest httpServletRequest;

    @Override
    public Optional<String> getCurrentAuditor() {
        String modifiedBy = httpServletRequest.getHeader("sub");
        // 수정 경로가 없으면 "unknown" 으로 설정
        if (!StringUtils.hasText(modifiedBy)) {
            modifiedBy = "unknown";
        }
        return Optional.of(modifiedBy);
    }
}