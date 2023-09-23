package com.enjoyservice.api.place.service;

import com.enjoyservice.api.place.dto.PlaceCsvInfoRes;
import com.enjoyservice.api.place.dto.PlaceInfoRes;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.place.entity.constant.GroupCode;
import com.enjoyservice.domain.place.entity.constant.GroupName;
import com.enjoyservice.domain.place.entity.type.*;
import com.enjoyservice.domain.place.service.PlaceService;
import com.enjoyservice.domain.placefavorite.service.service.PlaceFavoriteService;
import com.enjoyservice.domain.placelike.entity.PlaceLike;
import com.enjoyservice.domain.placelike.service.PlaceLikeService;
import com.enjoyservice.global.error.ErrorCode;
import com.enjoyservice.global.error.exception.BusinessException;
import com.enjoyservice.mapper.place.PlaceMapper;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceApiService {

    private final PlaceService placeService;
    private final PlaceLikeService placeLikeService;
    private final PlaceFavoriteService placeFavoriteService;

    private final int BATCH_SIZE = 100000; // 자를 크기를 설정

    public PlaceInfoRes getPlaceInfo(String memberId, long placeId) {
        Place place = placeService.findOneByIdFetchImage(placeId).get(0);
        List<PlaceLike> placeLikeResults = placeLikeService.findAllByPlaceAndActiveIsTrue(place);
        int likeCount = placeLikeResults.size();
        boolean likeFlag = isMemberLikePlace(memberId, placeLikeResults);
        boolean favoriteFlag = placeFavoriteService.existsByMemberIdAndPlace(memberId, place);

        return PlaceMapper.toPlaceInfoRes(place, likeCount, likeFlag, favoriteFlag);
    }

    private boolean isPresentOnePlace(List<Place> places) {
        return places.size() == 1;
    }

    private boolean isMemberLikePlace(String memberId, List<PlaceLike> likes) {
        return likes.stream()
                .anyMatch(placeLike -> placeLike.getMemberId().equals(memberId));
    }

    @Transactional
    public void placeCsvToDb(MultipartFile file) {
        if (!StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), ".csv")) {
            throw new BusinessException(ErrorCode.INVALID_GROUP_CODE);
        }

        try {
            CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

            // Use CsvToBean to map CSV records to a list of objects
            CsvToBean<PlaceCsvInfoRes> csvToBean = new CsvToBeanBuilder<PlaceCsvInfoRes>(reader)
                    .withType(PlaceCsvInfoRes.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<PlaceCsvInfoRes> parse = csvToBean.parse();
            List<Place> placeList = parse.stream()
                    .map(placeDto ->
                            generatePlace(placeDto)
                    )
                    .collect(Collectors.toList());

            int batchSize = BATCH_SIZE; // 자를 크기를 설정
            int listSize = placeList.size();

            List<List<Place>> resultList = new ArrayList<>();

            for (int i = 0; i < listSize; i += batchSize) {
                int fromIndex = i;
                int toIndex = Math.min(i + batchSize, listSize);

                List<Place> sublist = placeList.subList(fromIndex, toIndex);
                resultList.add(sublist);
            }

            for (List<Place> places : resultList) {
                placeService.saveAll(places);
            }

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_FORMAT);
        }

    }

    private static Place generatePlace(PlaceCsvInfoRes placeDto) {
        return Place.builder()
                .kakaoId(KakaoId.from(placeDto.getKakaoId()))
                .name(Name.from(placeDto.getName()))
                .category(Category.builder()
                        .groupCode(GroupCode.from(placeDto.getCategoryGroupCode()))
                        .groupName(GroupName.from(placeDto.getCategoryGroupName()))
                        .detail(Detail.from(placeDto.getCategoryName()))
                        .build())
                .phone(Phone.from(placeDto.getPhone()))
                .url(Url.from(placeDto.getUrl()))
                .address(Address.builder()
                        .name(placeDto.getAddressName())
                        .roadName(RoadName.from(placeDto.getAddressRoadName()))
                        .build())
                .position(Position.builder()
                        .latitude(Latitude.from(placeDto.getLatitude()))
                        .longitude(Longitude.from(placeDto.getLongitude()))
                        .build())
                .active(Active.from(true))
                .build();
    }

}
