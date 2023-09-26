import { useEffect, useState } from 'react';
import Footer from "../components/common/Footer";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import { CourseDetailPageWrapper } from "../styles/SCourseDeatilPage";
import CourseDetailList from "../components/enjoyHome/CourseDetail/CoursDetailList/CourseDetailList";
import { useParams } from "react-router";
import axios from 'axios';
import Map from '../components/common/map/Map';

const CourseDetailPage = () => {
    const [place_list, setPlaceList] = useState<any[]>([]);
    const {courseId} = useParams()
    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`https://nubi0.com/enjoy/v1/enjoy/course/${courseId}`);
                setPlaceList(response.data.data.place_list);
            } catch (err) {
                console.error(err);
            }
        };

        fetchData();
        const { x, y } = place_list[0];
        console.log(x, y);
        console.log(place_list);
        window.map.setCenter(new kakao.maps.LatLng(Number(y), Number(x)));
    }, [courseId]);
    return(
        <CourseDetailPageWrapper>
            <EnjoyHeader pageName="코스 이름" />
            <Map />
            <CourseDetailList place_list={place_list}/>
            <Footer />
        </CourseDetailPageWrapper>
    )
}

export default CourseDetailPage;