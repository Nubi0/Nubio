package com.enjoyservice.mapper.course;

import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.api.course.dto.CourseListRes;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;
import com.enjoyservice.domain.place.entity.Place;
import com.enjoyservice.domain.tag.entity.Tag;
import org.springframework.data.domain.Pageable;

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

    public static CourseListRes.CourseInfo convertToCourseInfo(Course course, List<Tag> tags, boolean favoriteFlag, int likeCount, boolean likeFlag) {
        return CourseListRes.CourseInfo.builder()
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
                            Integer kakaoId = place.getKakaoId().getValue();
                            String placeName = place.getName().getValue();
                            String imageUrl = place.getImages().get(0).getUrl().getValue();

                            return CourseListRes.CourseInfo.PlaceInfo.builder()
                                    .placeId(id)
                                    .kakaoId(kakaoId)
                                    .placeName(placeName)
                                    .imageUrl(imageUrl)
                                    .build();
                        })
                        .toList())
                .build();
    }

}
