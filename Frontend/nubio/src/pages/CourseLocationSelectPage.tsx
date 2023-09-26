import { useState, useEffect } from 'react';
import { CourseLocationSelectPageWrapper } from "../styles/SCourseSelectPage";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import CourseLocationSelect from "../components/enjoyHome/MyCourse/CourseSelect/CourseLocationSelect";
import Footer from "../components/common/Footer";
import CourseSelectModal from "../components/enjoyHome/MyCourse/CourseSelect/CourseSelectModal";
import { useDispatch } from 'react-redux';
import { resetPosition } from '../redux/slice/EnjoySlice';

const CourseLocationSelectPage = () => {
    const [modal, setModal] = useState(false);
    const toggleModal = () => {
        setModal(!modal);
    }
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(resetPosition())
    })
    return(
        <CourseLocationSelectPageWrapper>
            <EnjoyHeader pageName="장소 선택" />
            <CourseLocationSelect setModal={toggleModal} />
            <Footer />
            {modal && <CourseSelectModal setModal={toggleModal} />}
        </CourseLocationSelectPageWrapper>
    )
}

export default CourseLocationSelectPage;