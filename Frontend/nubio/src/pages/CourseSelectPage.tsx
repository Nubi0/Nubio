import { CourseSelectPageWrapper } from "../styles/SCourseSelectPage";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import SearchBar from "../components/common/SearchBar";
import CourseSelect from "../components/enjoyHome/MyCourse/CourseSelect/CourseSelect";
import Footer from "../components/common/Footer";

const CourseSelectPage = () => {
    return(
        <CourseSelectPageWrapper>
            <EnjoyHeader pageName="코스 선택" />
            <SearchBar />
            <CourseSelect />
            <Footer />
        </CourseSelectPageWrapper>
    );
}

export default CourseSelectPage;