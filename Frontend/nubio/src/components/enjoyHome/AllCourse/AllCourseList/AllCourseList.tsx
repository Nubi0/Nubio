import axios from "axios";
import { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { setCourseList } from "../../../../redux/slice/EnjoySlice";
import { AllCourseListWrapper } from "../../../../styles/SAllCoursePage";
import AllCourseItem from "./AllCourseItem";

const AllCourseList = ({active}: {active: any}) => {
    const course_list = 
        [
            {
                "title" : "코스 제목1",
                "course_tags" : [ "카페", "데이트", "단맛" ],
                "favorite_flag" : false,
                "like_count" : 10,
                "course_id": '1',
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
    const dispatch = useDispatch();
    const courseList = useSelector((state: any) => state.enjoy.courseList);
    useEffect(() => {
        if (active.all === true) {
          axios
            .get('https://nubi0.com/enjoy/v1/enjoy/course?region=DAEGU')
            .then((res) => {
              dispatch(setCourseList(res.data.data.course_list));
            })
            .catch((err) => {
              console.error(err);
            });
        } else {
          axios
            .get('https://nubi0.com/enjoy/v1/enjoy/course?region=DAEGU&sort=likeCount')
            .then((res) => {
              dispatch(setCourseList(res.data.data.course_list));
            })
            .catch((err) => {
              console.error(err);
            });
        }
      }, [active]);
    
    return(
        <AllCourseListWrapper>
            {course_list.map((value: CourseItem, index: number) => (
            <AllCourseItem key={index} value={value} />
        ))}
        </AllCourseListWrapper>
    );
}

export default AllCourseList;