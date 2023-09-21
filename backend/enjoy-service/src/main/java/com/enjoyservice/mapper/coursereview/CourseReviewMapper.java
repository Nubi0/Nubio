package com.enjoyservice.mapper.coursereview;

import com.enjoyservice.api.coursereview.dto.CourseReviewInfosRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CourseReviewMapper {

    public static CourseReviewInfosRes toCourseReviewInfosRes(List<CourseReviewInfosRes.CourseReviewInfo> courseReviewInfos, Long totalElements, Pageable pageable) {
        long totalPages = (long)(totalElements / pageable.getPageSize()) + 1;
        CourseReviewInfosRes.Meta meta = CourseReviewInfosRes.Meta.builder()
                .pageNumber(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalPages(totalPages)
                .totalElements(totalElements)
                .lastFlag(pageable.getPageNumber() == totalPages)
                .build();

        return CourseReviewInfosRes.builder()
                .courseReviewInfos(courseReviewInfos)
                .meta(meta)
                .build();
    }
}
