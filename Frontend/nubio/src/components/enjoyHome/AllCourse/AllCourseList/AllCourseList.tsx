import axios from "axios";
import { useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { setCourseList } from "../../../../redux/slice/EnjoySlice";
import { AllCourseListWrapper } from "../../../../styles/SAllCoursePage";
import AllCourseItem from "./AllCourseItem";

const AllCourseList = ({active}: {active: any}) => {
    const dispatch = useDispatch();
    const courseList = useSelector((state: any) => state.enjoy.courseList);
    const region = useSelector((state: any) => state.enjoy.region);
    useEffect(() => {
        if (active.all === true) {
          axios
            .get(`https://nubi0.com/enjoy/v1/enjoy/course?region=${region}`)
            .then((res) => {
              dispatch(setCourseList(res.data.data.course_list));
            })
            .catch((err) => {
              console.error(err);
            });
        } else {
          axios
            .get(`https://nubi0.com/enjoy/v1/enjoy/course?region=${region}&sort=likeCount`)
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
            {courseList.map((value: CourseItem, index: number) => (
            <AllCourseItem key={index} value={value} />
        ))}
        </AllCourseListWrapper>
    );
}

export default AllCourseList;