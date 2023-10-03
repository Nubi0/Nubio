import { useEffect, useState } from 'react';
import Footer from '../components/common/Footer';
import EnjoyHeader from '../components/enjoyHome/common/EnjoyHeader';
import { CourseDetailPageWrapper } from '../styles/SCourseDeatilPage';
import CourseDetailList from '../components/enjoyHome/CourseDetail/CoursDetailList/CourseDetailList';
import { useParams } from 'react-router';
import axios from 'axios';
import Map from '../components/common/map/Map';
import CourseButton from '../components/enjoyHome/CourseDetail/CoursDetailList/CourseButton';

const CourseDetailPage = () => {
  const [place_list, setPlaceList] = useState<any[]>([]);
  const [course_info, setCourseInfo] = useState<any>({});
  const { courseId } = useParams();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `https://nubi0.com/enjoy/v1/enjoy/course/${courseId}`
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
      <CourseDetailList place_list={place_list} course_info={course_info} />
      <CourseButton course_info={course_info} />
      <Footer />
    </CourseDetailPageWrapper>
  );
};

export default CourseDetailPage;
