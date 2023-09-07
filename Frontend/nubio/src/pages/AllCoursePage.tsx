import AllCourse from "../components/enjoyHome/AllCourse/AllCourse"
import Footer from "../components/common/Footer";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import { AllCoursePageWrapper } from "../styles/SAllCoursePage";

const AllCoursePage = () => {
    return(
        <AllCoursePageWrapper>
            <EnjoyHeader pageName="" />
            <AllCourse />
            <Footer />
        </AllCoursePageWrapper>
    )
}

export default AllCoursePage;