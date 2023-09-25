package com.authenticationservice.api.profile.service;

import com.authenticationservice.domain.member.entity.Member;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileUploadService {
    void uploadProfile(String category, Member member, MultipartFile file);
}
