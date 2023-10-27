import { MyCourseWrapper, MyCourseHeader } from "../../../styles/SMyCoursePage";
import LocationList from "./LocationList/LocationList";

const MyCourse = () => {
  return (
    <MyCourseWrapper>
      <MyCourseHeader>지역을 선택해주세요</MyCourseHeader>
      <LocationList />
    </MyCourseWrapper>
  );
};

export default MyCourse;
