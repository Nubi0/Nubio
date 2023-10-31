import { MyCourseWrapper, MyCourseHeader } from "../../../styles/SMyCoursePage";
import AllCourseLocationList from "./allCourseLocation/AllCourseLocationList";

const AllCourseLocation = () => {
  return (
    <MyCourseWrapper>
      <MyCourseHeader>지역을 선택해주세요</MyCourseHeader>
      <AllCourseLocationList />
    </MyCourseWrapper>
  );
};

export default AllCourseLocation;
