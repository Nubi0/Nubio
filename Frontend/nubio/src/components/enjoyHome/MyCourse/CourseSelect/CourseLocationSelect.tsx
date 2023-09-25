import { CourseLocationSelectWrapper, CourseMaker } from "../../../../styles/SCourseSelectPage";
import Map from "../../../common/map/Map";
import CoursePinList from "./CourseList";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router";

const CourseLocationSelect = () => {
     const positions = useSelector((state: any) => state.enjoy.positions);
     const navigate = useNavigate();
    return(
        <CourseLocationSelectWrapper>
            <Map />
            <CourseMaker onClick={() => navigate('/enjoy/mycourse/select')}>
                코스 그리기
            </CourseMaker>
            <CoursePinList positions={positions} />
        </CourseLocationSelectWrapper>
    )
}

export default CourseLocationSelect;