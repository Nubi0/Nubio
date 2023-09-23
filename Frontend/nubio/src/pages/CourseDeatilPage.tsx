import Footer from "../components/common/Footer";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import { CourseDetailPageWrapper } from "../styles/SCourseDeatilPage";
import CourseDetailList from "../components/enjoyHome/CourseDetail/CoursDetailList/CourseDetailList";
const CourseDetailPage = () => {
    return(
        <CourseDetailPageWrapper>
            <EnjoyHeader pageName="코스 이름" />
            <CourseDetailList/>
            <Footer />
        </CourseDetailPageWrapper>
    )
}

export default CourseDetailPage;