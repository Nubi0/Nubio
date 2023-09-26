package com.authenticationservice.domain.profile.service.impl;

import com.authenticationservice.domain.profile.entity.Profile;
import com.authenticationservice.domain.profile.entity.type.Active;
import com.authenticationservice.domain.profile.entity.type.FileName;
import com.authenticationservice.domain.profile.entity.type.FileSize;
import com.authenticationservice.domain.profile.entity.type.FileUrl;
import com.authenticationservice.domain.profile.repository.ProfileRepository;
import com.authenticationservice.domain.profile.service.ProfileService;
import com.authenticationservice.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("ProfileService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public Profile saveAccuseFile(String fileName, String url, Long fileSize, Member member) {
        Profile accuseFile = Profile.builder()
                .fileName(FileName.from(fileName))
                .fileUrl(FileUrl.from(url))
                .member(member)
                .fileSize(FileSize.from(fileSize))
                .active(Active.from(true))
                .build();
        Profile savedProfile = profileRepository.save(accuseFile);
        member.updateImage(savedProfile);
        return savedProfile;
    }
}
