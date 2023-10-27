import { useEffect, useState } from "react";
import Footer from "../components/common/Footer";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import { CourseDetailPageWrapper } from "../styles/SCourseDeatilPage";
import CourseDetailList from "../components/enjoyHome/courseDetail/coursDetailList/CourseDetailList";
import { useParams } from "react-router";
import axios from "axios";
import Map from "../components/common/map/Map";
import CourseButton from "../components/enjoyHome/courseDetail/coursDetailList/CourseButton";

const CourseDetailPage = () => {
  const [place_list, setPlaceList] = useState<placeProps[]>([]);
  const [course_info, setCourseInfo] = useState<courseInfo>({
    course_id: "",
    course_tags: [],
    favorite_flag: false,
    like_count: 0,
    like_flag: false,
  });
  const { courseId } = useParams();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          process.env.REACT_APP_SERVER_URL +
            `/enjoy/v1/enjoy/course/${courseId}`,
        );
        setPlaceList(response.data.data.place_list);
        setCourseInfo(response.data.data.course_info);
      } catch (err) {
        console.error(err);
      }
    };

    fetchData();
  }, [courseId]);

  useEffect(() => {
    // Check if place_list is not empty before accessing its elements
    if (place_list.length > 0) {
      const { x, y } = place_list[0];
      window.map.setCenter(new kakao.maps.LatLng(Number(y) - 0.002, Number(x)));
    }
  }, [place_list]);
  return (
    <CourseDetailPageWrapper>
      <EnjoyHeader pageName="코스 이름" />
      <Map />
      <CourseDetailList place_list={place_list} />
      <CourseButton
        course_info={course_info}
        setCourseInfo={setCourseInfo}
        setPlaceList={setPlaceList}
      />
      <Footer />
    </CourseDetailPageWrapper>
  );
};

export default CourseDetailPage;
