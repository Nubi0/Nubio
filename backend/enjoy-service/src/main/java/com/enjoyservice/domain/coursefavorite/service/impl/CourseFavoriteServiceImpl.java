package com.enjoyservice.domain.coursefavorite.service.impl;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursefavorite.entity.CourseFavorite;
import com.enjoyservice.domain.coursefavorite.entity.type.Active;
import com.enjoyservice.domain.coursefavorite.repository.CourseFavoriteRepository;
import com.enjoyservice.domain.coursefavorite.service.CourseFavoriteService;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourseFavoriteServiceImpl implements CourseFavoriteService {

    private final CourseFavoriteRepository courseFavoriteRepository;

    @Override
    public boolean existsByCourseAndMemberId(Course course, String memberId) {
        return courseFavoriteRepository.existsByCourseAndMemberIdAndActive(course, memberId, Active.from(true));
    }

    @Transactional
    @Override
    public boolean changeCourseFavoriteState(String memberId, Course course) {
        Optional<CourseFavorite> opCourseFavorite = courseFavoriteRepository.findByMemberIdAndCourse(memberId, course);
        // 이미 해당 장소를 좋아요 한 기록이 있으면
        if(opCourseFavorite.isPresent()) {
            CourseFavorite courseFavorite = opCourseFavorite.get();
            // 상태 변화
            return courseFavorite.changeActiveValue();
        }
        // 아직 해당 장소를 한 번도 좋아요 한 적이 없으면 새로 만들어서 결과 반환
        return createCourseFavorite(memberId, course);
    }

    private boolean createCourseFavorite(String memberId, Course course) {
        CourseFavorite courseFavorite = CourseFavorite.builder()
                .memberId(memberId)
                .course(course)
                .build();
        // 새로 만들어서 결과 반환
        courseFavoriteRepository.save(courseFavorite);
        return courseFavorite.getActive().isValue();
    }
}
