package com.chattingservice.domain.profile.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.profile.entity.Profile;
import com.chattingservice.domain.profile.entity.type.FileSize;
import com.chattingservice.domain.profile.entity.type.FileUrl;
import com.chattingservice.domain.profile.repository.ProfileRepository;
import com.chattingservice.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    @Override
    public Profile uploadProfile(String fileName, String url, Long fileSize, Participant participant) {
        Profile profile = profileRepository.findByParticipant(participant);
        profile.updateProfile(FileUrl.from(url), FileSize.from(fileSize));
        return profile;
    }
}
