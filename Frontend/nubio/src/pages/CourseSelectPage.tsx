import { useState } from "react";
import { CourseSelectPageWrapper } from "../styles/SCourseSelectPage";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import CourseSelect from "../components/enjoyHome/myCourse/courseSelect/CourseSelect";
import Footer from "../components/common/Footer";
import CourseSelectModal from "../components/enjoyHome/myCourse/courseSelect/CourseSelectModal";

const CourseSelectPage = () => {
  const [modal, setModal] = useState(false);
  const toggleModal = () => {
    setModal(!modal);
  };
  return (
    <CourseSelectPageWrapper>
      <EnjoyHeader pageName="코스 선택" />
      <CourseSelect />
      <Footer />
      {modal && <CourseSelectModal setModal={toggleModal} />}
    </CourseSelectPageWrapper>
  );
};

export default CourseSelectPage;
