import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import Footer from "../components/common/Footer";
import MyCourse from "../components/enjoyHome/MyCourse/MyCourse";
import { MyCoursePageWrapper } from "../styles/SMyCoursePage";

const MyCoursePage = () => {
    return(
        <MyCoursePageWrapper>
            <EnjoyHeader pageName="코스 만들기" />
            <MyCourse />
            <Footer />
        </MyCoursePageWrapper>
    )
}

export default MyCoursePage;