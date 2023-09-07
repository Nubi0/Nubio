import AllCourse from "../components/enjoyHome/AllCourse/AllCourse"
import Footer from "../components/common/Footer";
import { AllCoursePageWrapper } from "../styles/SAllCoursePage";

const AllCoursePage = () => {
    return(
        <AllCoursePageWrapper>
            <AllCourse />
            <Footer />
        </AllCoursePageWrapper>
    )
}

export default AllCoursePage;