package com.enjoyservice.mapper.course;

import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.dto.CourseDetailRes;
import com.enjoyservice.api.course.dto.CourseListRes;
import com.enjoyservice.domain.course.dto.PlaceInCourseInfoDto;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.tag.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CourseMapper {

    public static Course courseCreateReqToCourse(CourseCreateReq request, String memberId) {
        return Course.builder()
                .title(Title.from(request.getTitle()))
                .content(Content.from(request.getContent()))
                .region(Region.from(request.getRegion()))
                .publicFlag(PublicFlag.from(request.isPublicFlag()))
                .memberId(memberId)
                .build();
    }

    public static CourseListRes courseToCourseListRes(List<CourseListRes.CourseInfo> courseInfos, Long totalElements, Pageable pageable) {
        long totalPages = (long)(totalElements / pageable.getPageSize()) + 1;
        CourseListRes.Meta meta = CourseListRes.Meta.builder()
                .pageNumber(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalPages(totalPages)
                .totalElements(totalElements)
                .lastFlag(pageable.getPageNumber() == totalPages)
                .build();

        return CourseListRes.builder()
                .courseInfos(courseInfos)
                .meta(meta)
                .build();
    }

    public static CourseListRes courseToCourseListRes(List<CourseListRes.CourseInfo> courseInfos,Page<Course> coursePage) {

        CourseListRes.Meta meta = CourseListRes.Meta.builder()
                .pageNumber(coursePage.getNumber())
                .pageSize(coursePage.getSize())
                .totalPages(coursePage.getTotalPages())
                .totalElements(coursePage.getTotalElements())
                .lastFlag(coursePage.isLast())
                .build();

        return CourseListRes.builder()
                .courseInfos(courseInfos)
                .meta(meta)
                .build();
    }

    public static CourseListRes.CourseInfo convertToCourseInfo(Course course, List<Tag> tags, boolean favoriteFlag, int likeCount, boolean likeFlag) {
        return CourseListRes.CourseInfo.builder()
                .courseId(course.getId())
                .title(course.getTitle().getValue())
                .courseTags(tags.stream()
                        .map(t -> t.getName().getValue())
                        .toList())
                .favoriteFlag(favoriteFlag)
                .likeCount(likeCount)
                .likeFlag(likeFlag)
                .placeInfos(course.getCoursePlaceSequences().stream()
                        .map(coursePlaceSequence -> {
                            Place place = coursePlaceSequence.getPlace();

                            Long id = place.getId();
                            Long kakaoId = place.getKakaoId().getValue();
                            String placeName = place.getName().getValue();
//                            String imageUrl = place.getImages().get(0).getUrl().getValue();

                            return CourseListRes.CourseInfo.PlaceInfo.builder()
                                    .placeId(id)
                                    .kakaoId(kakaoId)
                                    .placeName(placeName)
//                                    .imageUrl(imageUrl)
                                    .build();
                        })
                        .toList())
                .build();
    }

    public static PlaceInCourseInfoDto toPlaceInfoInCourseDto(Place place) {
        int sequence = place.getSequences().get(0).getSequenceNumber().getValue();
        return PlaceInCourseInfoDto.builder()
                .id(place.getId())
                .kakaoId(place.getKakaoId().getValue())
                .addressName(place.getAddress().getName())
                .categoryGroupCode(place.getCategory().getGroupCode().toString())
                .categoryGroupName(place.getCategory().getGroupName().toString())
                .phone(place.getPhone().getValue())
                .placeName(place.getName().getValue())
                .placeUrl(place.getUrl().getValue())
                .roadAddressName(place.getAddress().getRoadName().getValue())
                .longitude(place.getPosition().getLongitude().getValue().toString())
                .latitude(place.getPosition().getLatitude().getValue().toString())
                .sequence(sequence)
                .build();
    }

    public static CourseDetailRes toCourseDetailRes(Course course, List<Tag> tags,
                                                    boolean favoriteFlag, int likeCount, boolean likeFlag,
                                                    List<PlaceInCourseInfoDto> placeInfoDtos) {
        return CourseDetailRes.builder()
                .courseInfo(
                        CourseDetailRes.CourseInfo.builder()
                                .courseId(course.getId())
                                .title(course.getTitle().getValue())
                                .content(course.getContent().getValue())
                                .courseTags(tags.stream().map(tag -> tag.getName().getValue()).toList())
                                .favoriteFlag(favoriteFlag)
                                .likeFlag(likeFlag)
                                .likeCount(likeCount)
                                .build()
                )
                .placeInfos(
                        placeInfoDtos.stream()
                                .map(p -> CourseDetailRes.PlaceInfo.builder()
                                        .id(p.getId())
                                        .kakaoId(p.getKakaoId())
                                        .addressName(p.getAddressName())
                                        .categoryGroupCode(p.getCategoryGroupCode())
                                        .categoryGroupName(p.getCategoryGroupName())
                                        .phone(p.getPhone())
                                        .placeName(p.getPlaceName())
                                        .placeUrl(p.getPlaceUrl())
                                        .roadAddressName(p.getRoadAddressName())
                                        .longitude(p.getLongitude())
                                        .latitude(p.getLatitude())
                                        .sequence(p.getSequence())
                                        .build())
                                .sorted(Comparator.comparingInt(CourseDetailRes.PlaceInfo::getSequence))
                                .toList()
                )
                .build();
    }

}
