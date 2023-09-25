import { CourseLocationSelectPageWrapper } from "../styles/SCourseSelectPage";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import CourseLocationSelect from "../components/enjoyHome/MyCourse/CourseSelect/CourseLocationSelect";
import Footer from "../components/common/Footer";

const CourseLocationSelectPage = () => {
    return(
        <CourseLocationSelectPageWrapper>
            <EnjoyHeader pageName="장소 선택" />
            <CourseLocationSelect />
            <Footer />
        </CourseLocationSelectPageWrapper>
    )
}

export default CourseLocationSelectPage;