package com.enjoyservice.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Long id;
    private String memberId;
    private String categoryDetails;
    private String categoryNames;
}
