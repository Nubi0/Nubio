import { AllCourseWrapper } from "../../../styles/SAllCoursePage";
import AllCourseHeader from "./AllCourseHeader";
import AllCourseList from "./AllCourseList/AllCourseList";
import Category from "./Category";

const AllCourse = () => {
    return(
        <AllCourseWrapper>
            <AllCourseHeader />
            <Category />
            <AllCourseList />
        </AllCourseWrapper>
    );
}

export default AllCourse;