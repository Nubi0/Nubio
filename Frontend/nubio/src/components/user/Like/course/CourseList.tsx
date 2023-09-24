import axios from 'axios';
import { useEffect, useState } from 'react';
import { CourseListWrapper } from "../../../../styles/SLikePage";
import AllCourseItem from "../../../enjoyHome/AllCourse/AllCourseList/AllCourseItem";

const CourseList = () => {
    const [cousre, setCourse] = useState<object[]>([])
    const course_list = 
        [
            {
                "title" : "코스 제목1",
                "course_tags" : [ "카페", "데이트", "단맛" ],
                "favorite_flag" : true,
                "like_count" : 10,
                "course_id": "1",
                "place_list" : [
                    {
                        "place_id" : 12,
                        "kakao_id" : 123456,
                        "place_name" : "장소 이름 1",
                        "img_url" : process.env.PUBLIC_URL + '/assets/dummy1.jpg',
                    },
                    {
                        "place_id" : 13,
                        "kakao_id" : 123453,
                        "place_name" : "장소 이름 2",
                        "img_url" : process.env.PUBLIC_URL + '/assets/dummy2.jpg',
                    },
                    {
                        "place_id" : 14,
                        "kakao_id" : 123454,
                        "place_name" : "장소 이름 3",
                        "img_url" : process.env.PUBLIC_URL + '/assets/dummy3.jpg',
                    },
                    {
                        "place_id" : 14,
                        "kakao_id" : 123454,
                        "place_name" : "장소 이름 3",
                        "img_url" : process.env.PUBLIC_URL + '/assets/dummy3.jpg',
                    },
                    {
                        "place_id" : 14,
                        "kakao_id" : 123454,
                        "place_name" : "장소 이름 3",
                        "img_url" : process.env.PUBLIC_URL + '/assets/dummy3.jpg',
                    },
                    {
                        "place_id" : 14,
                        "kakao_id" : 123454,
                        "place_name" : "장소 이름 3",
                        "img_url" : process.env.PUBLIC_URL + '/assets/dummy3.jpg',
                    }
                ]
            },
    ];
    useEffect(() => {
        axios.get('https://nubi0.com/enjoy/v1/profile')
              .then((res) => {
                setCourse(res.data.data.course_list);
              })
              .catch((err) => {
                console.error(err);
              })
    })

    return(
        <CourseListWrapper>
            {course_list.map((value, index) => {
                    return(
                        <AllCourseItem key={index} value={value} />
                    );
                })
            }
        </CourseListWrapper>
    );
}

export default CourseList;