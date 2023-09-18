package com.enjoyservice.mapper.course;

import com.enjoyservice.api.course.dto.CourseCreateReq;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.course.entity.constant.Region;
import com.enjoyservice.domain.course.entity.type.Content;
import com.enjoyservice.domain.course.entity.type.PublicFlag;
import com.enjoyservice.domain.course.entity.type.Title;

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

}
