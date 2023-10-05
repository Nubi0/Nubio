package com.authenticationservice.domain.profile.service.impl;

import com.authenticationservice.domain.profile.entity.Profile;
import com.authenticationservice.domain.profile.entity.type.Active;
import com.authenticationservice.domain.profile.entity.type.FileName;
import com.authenticationservice.domain.profile.entity.type.FileSize;
import com.authenticationservice.domain.profile.entity.type.FileUrl;
import com.authenticationservice.domain.profile.repository.ProfileRepository;
import com.authenticationservice.domain.profile.service.ProfileService;
import com.authenticationservice.domain.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
        Profile profile = profileRepository.findByMember(member);
        profile.updateProfile(FileUrl.from(url), FileSize.from(fileSize));
        return profile;
    }
}
