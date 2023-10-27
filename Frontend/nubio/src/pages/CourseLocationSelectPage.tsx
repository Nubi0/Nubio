import { useState } from "react";
import { CourseLocationSelectPageWrapper } from "../styles/SCourseSelectPage";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import CourseLocationSelect from "../components/enjoyHome/myCourse/courseSelect/CourseLocationSelect";
import Footer from "../components/common/Footer";
import CourseSelectModal from "../components/enjoyHome/myCourse/courseSelect/CourseSelectModal";

const CourseLocationSelectPage = () => {
  const [modal, setModal] = useState(false);
  const toggleModal = () => {
    setModal(!modal);
  };
  return (
    <CourseLocationSelectPageWrapper>
      <EnjoyHeader pageName="장소 선택" />
      <CourseLocationSelect setModal={toggleModal} />
      <Footer />
      {modal && <CourseSelectModal setModal={toggleModal} />}
    </CourseLocationSelectPageWrapper>
  );
};

export default CourseLocationSelectPage;
