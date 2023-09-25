import axios from "axios";
import { CustomCouresListWrapper } from "../../../../styles/SEnjoyHomePage";
import CustomCourseItem from "./CustomCourseItem";
import { useEffect } from 'react';
import { useDispatch } from "react-redux";
import { setCourseList } from "../../../../redux/slice/EnjoySlice";

const CustomCourseList = () => {
    const dispatch = useDispatch();
    const itemList: CourseItem[] = [
        {
            title: '더미1 코스',
            like_count: 10,
            favorite_flag: true,
            course_id: "1",
            place_list: [
                {
                    place_id: 12,
                    kakao_id: 123456,
                    place_name: '장소이름',
                    img_url: process.env.PUBLIC_URL + '/assets/dummy/dummy1.jpg'
                }
            ],
            course_tags: ['카페', '데이트', '단맛']
        },
        {
            title: '더미2 코스',
            like_count: 0,
            favorite_flag: true,
            course_id: "2",
            place_list: [
                {
                    place_id: 12,
                    kakao_id: 123456,
                    place_name: '장소이름',
                    img_url: process.env.PUBLIC_URL + '/assets/dummy/dummy2.jpg'
                }
            ],
            course_tags: ['카페', '데이트', '단맛']
        },
        {
            title: '더미3 코스',
            like_count: 40,
            favorite_flag: false,
            course_id: "3",
            place_list: [
                {
                    place_id: 12,
                    kakao_id: 123456,
                    place_name: '장소이름',
                    img_url: process.env.PUBLIC_URL + '/assets/dummy/dummy3.jpg'
                }
            ],
            course_tags: ['카페', '데이트', '단맛']
        },
    ]
    useEffect(() => {
        axios.post('https://nubi0.com/enjoy/v1/enjoy',    {"longitude" : 128.5934, "latitude" : 35.8556})
            .then((res) => {
                console.log(res)
                dispatch(setCourseList(res.data.course_list));
            })
            .catch((err) => {
                console.error(err);
            })

    })
    return(
        <CustomCouresListWrapper>
            {itemList.map((value: CourseItem, index: number) => (
                <CustomCourseItem value={value} key={index} />
            ))}
        </CustomCouresListWrapper>
    );
}

export default CustomCourseList;