package com.enjoyservice.api.recommendation.service.impl;

import com.enjoyservice.api.recommendation.client.FastApiClient;
import com.enjoyservice.api.recommendation.client.KakaoMapClient;
import com.enjoyservice.api.recommendation.dto.RecommendationReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastCreateReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoRes;
import com.enjoyservice.api.recommendation.dto.kakao.ClientDto;
import com.enjoyservice.api.recommendation.service.RecommendationApiService;
import com.enjoyservice.domain.course.dto.CourseDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import com.enjoyservice.domain.membertaste.service.MemberTasteService;
import com.enjoyservice.domain.recomendation.entity.Words;
import com.enjoyservice.domain.recomendation.service.WordsService;
import com.querydsl.core.Tuple;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
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
    public void saveModel(String region) {
        processMakeModel(region);
    }

    @Override
    public FastRecoRes getCourses(String memberId ,RecommendationReq recommendationReq) {
        List<String> courseInfo = new ArrayList<>();
        List<MemberTaste> courseMemberTaste = memberTasteService.findByMemberId(memberId);
        processMemberTaste(courseMemberTaste, courseInfo);
        ClientDto regionReq = kakaoMapClient.getRegion(appKey, recommendationReq.getLongitude(),
                recommendationReq.getLatitude());
        log.info("address = {}", regionReq.getDocuments().get(0).getRegion_1depth_name());
        log.info("trans to = {}", Region.check(regionReq.getDocuments().get(0).getRegion_1depth_name()).name());
        FastRecoRes recoCourses = fastApiClient.getReco(FastRecoReq.of(
                Region.check(regionReq.getDocuments().get(0).getRegion_1depth_name()).name(), courseInfo));
        return recoCourses;
    }


    private void processMemberTaste(List<MemberTaste> courseMemberTaste, List<String> courseInfo) {
        for (MemberTaste memberTaste : courseMemberTaste) {
            courseInfo.add(memberTaste.getTaste().getDetailType().getDescription());
        }
    }

    private void processPlaceDetail(String detailsStr, List<String> courseInfo) {

        String[] details = detailsStr.split(",");
        for (String detail : details) {
            courseInfo.add(detail);
        }
    }

    private void processPlaceName(String namesStr, List<String> courseInfo) {
        String[] names = namesStr.split(",");
        for (String name : names) {
            courseInfo.add(name);
        }
    }

    private void processMakeModel(String region) {
        List<Words> words = new ArrayList<>();
        List<CourseDto> courses = courseService.findAllByRegionToModel(Region.from(region));
        log.info("course length = {}", courses.size());
        for (CourseDto course : courses) {
            List<String> courseInfo = new ArrayList<>();
            List<MemberTaste> courseMemberTaste = memberTasteService.findByMemberId(course.getMemberId());

            processMemberTaste(courseMemberTaste, courseInfo);
            processPlaceDetail(course.getCategoryDetails(), courseInfo);
            processPlaceName(course.getCategoryNames(), courseInfo);

            words.add(Words.of(course.getId(), courseInfo));
        }
        wordsService.saveWords(words);
    }
}
