package com.enjoyservice.api.coursereview.service.impl;

import com.enjoyservice.api.coursereview.dto.CourseReviewCreate;
import com.enjoyservice.api.coursereview.dto.CourseReviewInfosRes;
import com.enjoyservice.api.coursereview.service.CourseReviewApiService;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.service.CourseService;
import com.enjoyservice.domain.coursereview.entity.CourseReview;
import com.enjoyservice.domain.coursereview.service.CourseReviewService;
import com.enjoyservice.external.authentication.client.MemberInfoClient;
import com.enjoyservice.external.authentication.dto.MemberInfoRes;
import com.enjoyservice.mapper.coursereview.CourseReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseReviewApiServiceImpl implements CourseReviewApiService {

    private final CourseService courseService;
    private final CourseReviewService courseReviewService;
    private final MemberInfoClient memberInfoClient;

    @Transactional
    @Override
    public CourseReviewCreate.Res createCourseReview(Long courseId, CourseReviewCreate.Req request, String memberId) {
        Course course = courseService.findById(courseId);
        Long savedCourseReviewId = courseReviewService.save(request.getContent(), request.getPoint(), course, memberId);
        return CourseReviewCreate.Res.builder()
                .courseReviewId(savedCourseReviewId)
                .build();
    }

    @Transactional
    @Override
    public void deleteCourseReview(Long courseReviewId) {
        courseReviewService.delete(courseReviewId);
    }

    @Override
    public CourseReviewInfosRes getCourseReviewInfos(Long courseId, Pageable pageable) {
        // 관련 코스 리뷰 모두 조회
        List<CourseReview> courseReviews = courseReviewService.findCourseReviewsByCourseId(courseId, pageable);
        // 리뷰들 작성자 닉네임 가져오기
        List<CourseReviewInfosRes.CourseReviewInfo> courseReviewInfos = new ArrayList<>();
        for(CourseReview courseReview : courseReviews) {
            String memberId = courseReview.getMemberId();
            MemberInfoRes memberInfoRes = memberInfoClient.requestMemberInfo(memberId);
            String nickname = memberInfoRes.getData().getNickname();
            CourseReviewInfosRes.CourseReviewInfo info = CourseReviewInfosRes.CourseReviewInfo.builder()
                    .courseReviewId(courseReview.getId())
                    .memberId(memberId)
                    .nickName(nickname)
                    .content(courseReview.getContent().getValue())
                    .point(courseReview.getPoint().getValue())
                    .build();
            courseReviewInfos.add(info);
        }

        // meta
        Long totalElements = courseReviewService.countCourseReviewsByCourseId(courseId);
        return CourseReviewMapper.toCourseReviewInfosRes(courseReviewInfos, totalElements, pageable);
    }
}
