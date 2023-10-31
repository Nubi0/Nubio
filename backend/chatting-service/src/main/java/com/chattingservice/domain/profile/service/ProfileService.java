package com.chattingservice.domain.profile.service;

import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.profile.entity.Profile;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {

    Profile uploadProfile(String fileName, String url, Long fileSize, Participant participant);

}
