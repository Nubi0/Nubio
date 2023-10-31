package com.chattingservice.api.profile.service;

import com.chattingservice.domain.participant.enity.Participant;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileUploadService {

    //프로필 업로드
    void uploadProfile(String category, Participant participant, MultipartFile file);



}
