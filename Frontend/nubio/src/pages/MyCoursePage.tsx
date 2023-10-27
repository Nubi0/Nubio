import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { resetPosition } from "../redux/slice/EnjoySlice";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import Footer from "../components/common/Footer";
import MyCourse from "../components/enjoyHome/myCourse/MyCourse";
import { MyCoursePageWrapper } from "../styles/SMyCoursePage";

const MyCoursePage = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(resetPosition());
  });
  return (
    <MyCoursePageWrapper>
      <EnjoyHeader pageName="코스 만들기" />
      <MyCourse />
      <Footer />
    </MyCoursePageWrapper>
  );
};

export default MyCoursePage;
