package com.enjoyservice.domain.placefavorite.service.service.impl;

import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.coursefavorite.entity.CourseFavorite;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placefavorite.entity.PlaceFavorite;
import com.enjoyservice.domain.placefavorite.repository.PlaceFavoriteRepository;
import com.enjoyservice.domain.placefavorite.service.service.PlaceFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceFavoriteServiceImpl implements PlaceFavoriteService {

    private final PlaceFavoriteRepository placeFavoriteRepository;

    @Override
    public boolean existsByMemberIdAndPlace(String memberId, Place place) {
        return placeFavoriteRepository.existsByMemberIdAndPlace(memberId, place);
    }

    @Transactional
    @Override
    public boolean changePlaceFavoriteState(String memberId, Place place) {
        Optional<PlaceFavorite> opPlaceFavorite = placeFavoriteRepository.findByMemberIdAndPlace(memberId, place);
        // 이미 해당 장소를 좋아요 한 기록이 있으면
        if(opPlaceFavorite.isPresent()) {
            PlaceFavorite placeFavorite = opPlaceFavorite.get();
            // 상태 변화
            return placeFavorite.changeActiveValue();
        }
        // 아직 해당 장소를 한 번도 좋아요 한 적이 없으면 새로 만들어서 결과 반환
        return createPlaceFavorite(memberId, place);
    }

    private boolean createPlaceFavorite(String memberId, Place place) {
        PlaceFavorite placeFavorite = PlaceFavorite.builder()
                .memberId(memberId)
                .place(place)
                .build();
        // 새로 만들어서 결과 반환
        placeFavoriteRepository.save(placeFavorite);
        return placeFavorite.getActive().isValue();
    }
}
