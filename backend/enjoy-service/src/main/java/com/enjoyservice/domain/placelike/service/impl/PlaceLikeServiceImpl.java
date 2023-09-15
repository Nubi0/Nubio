package com.enjoyservice.domain.placelike.service.impl;

import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import com.enjoyservice.domain.placelike.repository.PlaceLikeRepository;
import com.enjoyservice.domain.placelike.service.PlaceLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceLikeServiceImpl implements PlaceLikeService {

    private final PlaceLikeRepository placeLikeRepository;

    @Override
    public List<PlaceLike> findAllByPlace(Place place) {
        return placeLikeRepository.findAllByPlace(place);
    }

    @Transactional
    @Override
    public boolean changePlaceLikeState(String memberId, Place place) {
        Optional<PlaceLike> opPlaceLike = placeLikeRepository.findByMemberIdAndPlace(memberId, place);
        // 이미 해당 장소를 좋아요 한 기록이 있으면
        if(opPlaceLike.isPresent()) {
            PlaceLike placeLike = opPlaceLike.get();
            // 상태 변화
            return placeLike.changeActiveValue();
        }
        // 아직 해당 장소를 한 번도 좋아요 한 적이 없으면 새로 만들어서 결과 반환
        return createPlaceLike(memberId, place);
    }

    private boolean createPlaceLike(String memberId, Place place) {
        PlaceLike placeLike = PlaceLike.builder()
                .memberId(memberId)
                .place(place)
                .build();
        // 새로 만들어서 결과 반환
        placeLikeRepository.save(placeLike);
        return placeLike.getActive().isValue();
    }
}
