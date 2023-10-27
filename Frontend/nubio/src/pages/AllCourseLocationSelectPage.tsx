import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import Footer from "../components/common/Footer";
import { MyCoursePageWrapper } from "../styles/SMyCoursePage";
import AllCourseLocation from "../components/enjoyHome/allCourse/AllCourseLocation";

const AllCourseLocationSelectPage = () => {
  return (
    <MyCoursePageWrapper>
      <EnjoyHeader pageName="지역 선택" />
      <AllCourseLocation />
      <Footer />
    </MyCoursePageWrapper>
  );
};

export default AllCourseLocationSelectPage;
