import axios from "axios";
import { useEffect, useState } from "react";
import { CourseListWrapper } from "@styles/SLikePage";
import AllCourseItem from "@/components/enjoyHome/allCourse/allCourseList/AllCourseItem";

const CourseList = () => {
  const [course, setCourse] = useState<CourseItem[]>([]);
  useEffect(() => {
    axios
      .post(
        process.env.REACT_APP_SERVER_URL +
          "/enjoy/v1/enjoy/profile/course/favorite",
      )
      .then((res) => {
        setCourse(res.data.data.course_list);
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  return (
    <CourseListWrapper>
      {course.map((value: CourseItem, index: number) => {
        return <AllCourseItem key={index} value={value} />;
      })}
    </CourseListWrapper>
  );
};

export default CourseList;
