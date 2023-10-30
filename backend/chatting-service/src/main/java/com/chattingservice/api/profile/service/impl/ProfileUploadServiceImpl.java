package com.chattingservice.api.profile.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.chattingservice.api.profile.service.ProfileUploadService;
import com.chattingservice.domain.participant.enity.Participant;
import com.chattingservice.domain.profile.service.ProfileService;
import com.chattingservice.global.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileUploadServiceImpl implements ProfileUploadService {

    private final AmazonS3Client amazonS3Client;
    private final ProfileService profileService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Transactional
    @Override
    public void uploadProfile(String category, Participant participant, MultipartFile file) {

        if (file != null) {
            LocalDateTime now = LocalDateTime.now();

            String fileName = FileUtils.buildFileNameForParticipant(category
                    , participant.getChattingRoom().getId(), participant.getId(), file.getOriginalFilename(), now);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {
                amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                String url = amazonS3Client.getUrl(bucketName, fileName).toString();

                profileService.uploadProfile(file.getOriginalFilename(), url, objectMetadata.getContentLength(), participant);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
