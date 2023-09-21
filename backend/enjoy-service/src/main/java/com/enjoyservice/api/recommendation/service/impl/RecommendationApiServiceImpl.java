package com.enjoyservice.api.recommendation.service.impl;

import com.enjoyservice.api.recommendation.client.FastApiClient;
import com.enjoyservice.api.recommendation.client.KakaoMapClient;
import com.enjoyservice.api.recommendation.dto.RecommendationReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastCreateReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoRes;
import com.enjoyservice.api.recommendation.dto.kakao.ClientDto;
import com.enjoyservice.api.recommendation.service.RecommendationApiService;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import com.enjoyservice.domain.membertaste.service.MemberTasteService;
import com.enjoyservice.domain.recomendation.entity.Words;
import com.enjoyservice.domain.recomendation.service.WordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendationApiServiceImpl implements RecommendationApiService {

    private final WordsService wordsService;
    private final CourseService courseService;
    private final MemberTasteService memberTasteService;
    private final FastApiClient fastApiClient;
    private final KakaoMapClient kakaoMapClient;

    @Value("${cloud.openfeign.client.config.feignName.appKey}")
    private String appKey;

    @Override
    @Transactional
    public void saveModel() {
        String[] regions = {"DAEGU", "SEOUL" ,"BUSAN", "DAEJEON", "GWANGJU"};
        for (String region : regions) {
            processMakeModel(region);
        }
    }

    @Override
    public FastRecoRes getCourses(String memberId ,RecommendationReq recommendationReq) {
        List<String> courseInfo = new ArrayList<>();
        List<MemberTaste> courseMemberTaste = memberTasteService.findByMemberId(memberId);
        processMemberTaste(courseMemberTaste, courseInfo);
        ClientDto regionReq = kakaoMapClient.getRegion(appKey, recommendationReq.getLongitude(), recommendationReq.getLatitude());
        FastRecoRes recoCourses = fastApiClient.getReco(FastRecoReq.of(
                Region.check(regionReq.getDocuments().get(0).getRegion_1depth_name()).name(), courseInfo));
        return recoCourses;
    }


    private void processMemberTaste(List<MemberTaste> courseMemberTaste, List<String> courseInfo) {
        for (MemberTaste memberTaste : courseMemberTaste) {
            courseInfo.add(memberTaste.getTaste().getDetailType().getDescription());
        }
    }

    private void processCoursePlace(List<CoursePlaceSequence> sequences, List<String> courseInfo) {
        for (CoursePlaceSequence sequence : sequences) {
            courseInfo.add(sequence.getPlace().getCategory().getDetail().getValue());
            courseInfo.add(sequence.getPlace().getCategory().getGroupName().name());
        }
    }
    private void processMakeModel(String region) {
        List<Words> words = new ArrayList<>();
        List<Course> courses = courseService.findAllByRegionToModel(Region.from(region));
        for (Course course : courses) {
            List<String> courseInfo = new ArrayList<>();
            List<MemberTaste> courseMemberTaste = memberTasteService.findByMemberId(course.getMemberId());

            processMemberTaste(courseMemberTaste, courseInfo);
            processCoursePlace(course.getCoursePlaceSequences(), courseInfo);

            courseInfo.add(course.getId().toString());
            words.add(Words.of(course.getId(), courseInfo));
        }
        wordsService.saveWords(words);
        fastApiClient.createModel(FastCreateReq.from(region));
    }
}
