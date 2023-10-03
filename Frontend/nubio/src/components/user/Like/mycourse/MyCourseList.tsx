import axios from 'axios';
import { useEffect, useState } from 'react';
import { CourseListWrapper } from '../../../../styles/SLikePage';
import AllCourseItem from '../../../enjoyHome/AllCourse/AllCourseList/AllCourseItem';

const MyCourseList = () => {
  const [course, setCourse] = useState<object[]>([]);
  console.log(course);
  useEffect(() => {
    axios
      .get(process.env.REACT_APP_SERVER_URL + '/enjoy/v1/enjoy/profile/course')
      .then((res) => {
        setCourse(res.data.data.course_list);
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  return (
    <CourseListWrapper>
      {course.map((value, index) => {
        return <AllCourseItem key={index} value={value} />;
      })}
    </CourseListWrapper>
  );
};

export default MyCourseList;
