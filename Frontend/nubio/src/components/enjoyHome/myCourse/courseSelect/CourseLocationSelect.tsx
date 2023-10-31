import Swal from "sweetalert2";
import { CourseLocationSelectWrapper, CourseMaker } from "../../../../styles/SCourseSelectPage";
import Map from "../../../common/map/Map";
import CourseList from "./CourseList";
import { useSelector } from "react-redux";

const CourseLocationSelect = ({setModal}: {setModal:  () => void}) => {
    const positions = useSelector((state: {enjoy: {positions: placeItem[]}}) => state.enjoy.positions);
    const handleSave = () => {
        if(positions.length < 3){
            Swal.fire({
                title: '장소가 부족합니다.',
                text: '2개 이상의 장소를 선택하세요.',
                icon: 'error',
            })
        } else {
            setModal();
        }
    }
    return(
        <CourseLocationSelectWrapper>
            <Map />
            <CourseMaker onClick={handleSave}>
                코스 저장 
            </CourseMaker>
            <CourseList positions={positions} />
        </CourseLocationSelectWrapper>
    )
}

export default CourseLocationSelect;