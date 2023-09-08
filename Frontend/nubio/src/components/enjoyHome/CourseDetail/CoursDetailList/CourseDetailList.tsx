import { CourseDetailListWrapper, ItemWrapper, DetailHr } from "../../../../styles/SCourseDeatilPage";
import CourseButton from "./CourseButton";
import CourseDetailItem from "./CourseDetailItem";

const CourseDetailList = () => {
    const place_list = [
        {
            "id": "1",
            "address_name": "경기 수원시 팔달구 인계동 1113-11",
            "category_group_code": "CT1",
            "category_group_name": "문화시설",
            "phone": "1544-1122",
            "place_name": "CGV 동수원",
            "place_url": "http://place.map.kakao.com/8552500",
            "img_url" : process.env.PUBLIC_URL + '/assets/cgv.jpg',
            "road_address_name": "경기 수원시 팔달구 권광로 181",
            "x": "127.03231003231717",
            "y": "37.26390632559984",
            "sequence" : 0
        },
        {
            "id": "2",
            "address_name": "경기 수원시 팔달구 인계동 1113-11",
            "category_group_code": "CT1",
            "category_group_name": "문화시설",
            "phone": "1544-1122",
            "place_name": "CGV 동수원",
            "place_url": "http://place.map.kakao.com/8552500",
            "img_url" : process.env.PUBLIC_URL + '/assets/cgv.jpg',
            "road_address_name": "경기 수원시 팔달구 권광로 181",
            "x": "127.03231003231717",
            "y": "37.26390632559984",
            "sequence" : 1
        }
    ]
    return(
        <CourseDetailListWrapper>
            <DetailHr />
            <ItemWrapper>
                {place_list.map((place, index) => {
                    return(
                        <CourseDetailItem key={index} place={place} />
                    );
                })}
            </ItemWrapper>
            <CourseButton />
        </CourseDetailListWrapper>
    );
};

export default CourseDetailList;