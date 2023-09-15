import { useState } from 'react';
import { CourseSelectPageWrapper } from "../styles/SCourseSelectPage";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import SearchBar from "../components/common/search/SearchBar";
import CourseSelect from "../components/enjoyHome/MyCourse/CourseSelect/CourseSelect";
import Footer from "../components/common/Footer";
import CourseSelectModal from "../components/enjoyHome/MyCourse/CourseSelect/CourseSelectModal";

const CourseSelectPage = () => {
    const [modal, setModal] = useState(false);
    const toggleModal = () => {
        setModal(!modal);
    }
    return(
        <CourseSelectPageWrapper>
            <EnjoyHeader pageName="코스 선택" />
            <SearchBar />
            <CourseSelect setModal={toggleModal} />
            <Footer />
            {modal && <CourseSelectModal setModal={toggleModal} />}
        </CourseSelectPageWrapper>
    );
}

export default CourseSelectPage;