package com.enjoyservice.mapper.profile;

import com.enjoyservice.api.profile.dto.MyCourseRes;
import com.enjoyservice.domain.course.entity.Course;
import com.enjoyservice.domain.courseplacesequence.entity.CoursePlaceSequence;
import com.enjoyservice.domain.place.entity.Place;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyCourseMapper {

    public static MyCourseRes PagedCourseToMyCourseRes(Page<Course> pagedCourses) {

        List<Course> courses = pagedCourses.getContent();
        List<MyCourseRes.CourseInfo> courseInfos = courses.stream()
                .map(course -> {
                    List<Place> places = course.getCoursePlaceSequences().stream()
                            .map(CoursePlaceSequence::getPlace)
                            .toList();
                    List<MyCourseRes.CourseInfo.PlaceInfo> placeInfos = places.stream()
                            .map(place -> {
                                return MyCourseRes.CourseInfo.PlaceInfo.builder()
                                        .placeId(place.getId())
                                        .kakaoId(place.getKakaoId().getValue())
                                        .placeName(place.getName().getValue())
                                        .imgUrl(place.getImages().stream().findFirst().isPresent()
                                                ? place.getImages().stream().map(img -> img.getUrl().getValue()).findFirst().get() : "not exist")
                                        .build();
                            })
                            .toList();

                    return MyCourseRes.CourseInfo.builder()
                            .courseId(course.getId())
                            .title(course.getTitle().getValue())
                            .tagInfos(course.getCourseTags().stream()
                                    .map(ct -> ct.getTag().getName().getValue())
                                    .collect(Collectors.toList()))
                            .placeInfos(placeInfos)
                            .build();
                })
                .toList();



//        List<MyCourseRes.CourseInfo> courseInfos = new ArrayList<>();
//        List<Course> courses = pagedCourses.getContent();
//        for(Course course : courses) {
//
//            List<Place> places = course.getCoursePlaceSequences().stream()
//                    .map(CoursePlaceSequence::getPlace)
//                    .toList();
//
//            List<MyCourseRes.CourseInfo.PlaceInfo> placeInfos = new ArrayList<>();
//            for(Place place : places) {
//                MyCourseRes.CourseInfo.PlaceInfo placeInfo = MyCourseRes.CourseInfo.PlaceInfo.builder()
//                        .placeId(place.getId())
//                        .kakaoId(place.getKakaoId().getValue())
//                        .placeName(place.getName().getValue())
//                        .imgUrl(place.getImages().stream().findFirst().isPresent()
//                                ? place.getImages().stream().map(img -> img.getUrl().getValue()).findFirst().toString() : "not exist")
//                        .build();
//                placeInfos.add(placeInfo);
//            }
//
//            MyCourseRes.CourseInfo courseInfo = MyCourseRes.CourseInfo.builder()
//                    .title(course.getTitle().getValue())
//                    .tagInfos(course.getCourseTags().stream()
//                            .map(ct -> ct.getTag().getName().getValue())
//                            .toList())
//                    .placeInfos(placeInfos)
//                    .build();
//            courseInfos.add(courseInfo);
//
//        }


        MyCourseRes.Meta meta = MyCourseRes.Meta.builder()
                .pageNumber(pagedCourses.getNumber())
                .pageSize(pagedCourses.getSize())
                .totalPages(pagedCourses.getTotalPages())
                .totalElements(pagedCourses.getTotalElements())
                .lastFlag(pagedCourses.isLast())
                .build();

        return MyCourseRes.builder()
                .courseInfos(courseInfos)
                .meta(meta)
                .build();
    }
}
