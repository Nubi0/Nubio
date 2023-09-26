package com.authenticationservice.domain.profile.service;

import com.authenticationservice.domain.profile.entity.Profile;
import com.authenticationservice.domain.member.entity.Member;

public interface ProfileService {

    Profile saveAccuseFile(String fileName, String fileUrl, Long fileSize, Member member);

}
