package com.enjoyservice.api.recommendation.service.impl;

import com.enjoyservice.api.recommendation.client.FastApiClient;
import com.enjoyservice.api.recommendation.client.KakaoMapClient;
import com.enjoyservice.api.recommendation.dto.CourseInfoDto;
import com.enjoyservice.api.recommendation.dto.PlaceInfoDto;
import com.enjoyservice.api.recommendation.dto.RecommendationReq;
import com.enjoyservice.api.recommendation.dto.RecommendationRes;
import com.enjoyservice.api.recommendation.dto.fastapi.FastCreateReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoReq;
import com.enjoyservice.api.recommendation.dto.fastapi.FastRecoRes;
import com.enjoyservice.api.recommendation.dto.kakao.ClientDto;
import com.enjoyservice.api.recommendation.service.RecommendationApiService;
import com.enjoyservice.domain.course.dto.CourseDto;
import com.enjoyservice.domain.course.dto.RecommendationPlaceDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.coursefavorite.service.CourseFavoriteService;
import com.enjoyservice.domain.courselike.entity.CourseLike;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.membertaste.entity.MemberTaste;
import com.enjoyservice.domain.membertaste.service.MemberTasteService;
import com.enjoyservice.domain.recomendation.entity.Words;
import com.enjoyservice.domain.recomendation.service.WordsService;
import com.enjoyservice.domain.tag.entity.Tag;
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
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final CourseFavoriteService courseFavoriteService;

    @Value("${cloud.openfeign.client.config.feignName.appKey}")
    private String appKey;

    @Override
    @Transactional
    public void saveModel(String region) {
        processMakeModel(region);
    }

    @Override
    public RecommendationRes getCourses(String memberId , RecommendationReq recommendationReq) {
        List<String> courseInfo = new ArrayList<>();
        List<MemberTaste> courseMemberTaste = memberTasteService.findByMemberId(memberId);
        processMemberTaste(courseMemberTaste, courseInfo);
        ClientDto regionReq = kakaoMapClient.getRegion(appKey, recommendationReq.getLongitude(),
                recommendationReq.getLatitude());
        FastRecoRes recoCourses = fastApiClient.getReco(FastRecoReq.of(
                Region.check(regionReq.getDocuments().get(0).getRegion_1depth_name()).name(), courseInfo));
        List<CourseInfoDto> courseInfos = extractCourseInfo(memberId, recoCourses);
        return RecommendationRes.from(courseInfos);
    }

    private List<CourseInfoDto> extractCourseInfo(String memberId, FastRecoRes recoCourses) {
        List<CourseInfoDto> result = new ArrayList<>();
        for(String courseId : recoCourses.getResult()) {
            Course course = courseService.findById(Long.parseLong(courseId));
            List<Tag> tags = courseService.findTags(course);
            List<String> tag = tags.stream()
                    .map(tag1 -> tag1.getName().getValue())
                    .collect(Collectors.toList());

            boolean favoriteFlag = courseFavoriteService.existsByCourseAndMemberId(course, memberId);
            List<CourseLike> courseLikes = courseService.findCourseLikesByCourse(course);
            int likeCount = courseLikes.size();
            boolean likeFlag = isMemberLikeCourse(memberId, courseLikes);
            List<RecommendationPlaceDto> placeByCourse = courseService.findPlaceByCourse(course);

            List<PlaceInfoDto> places = placeByCourse.stream()
                    .map(PlaceInfoDto::from)
                    .collect(Collectors.toList());
            log.info("places = {} ", places.get(0).getPlaceName());

            CourseInfoDto courseInformation = CourseInfoDto.of(
                    course.getId(), course.getTitle().getValue(), tag, favoriteFlag, likeCount, likeFlag, places);
            result.add(courseInformation);
        }
        return result;
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

    private boolean isMemberLikeCourse(String memberId, List<CourseLike> likes) {
        return likes.stream()
                .filter(Objects::nonNull)
                .anyMatch(courseLike -> courseLike.getMemberId().equals(memberId));
    }
}
