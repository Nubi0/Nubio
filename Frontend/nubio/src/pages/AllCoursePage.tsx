import { useState } from 'react';
import AllCourse from '../components/enjoyHome/AllCourse/AllCourse';
import Footer from '../components/common/Footer';
import EnjoyHeader from '../components/enjoyHome/common/EnjoyHeader';
import { AllCoursePageWrapper } from '../styles/SAllCoursePage';
import AllCourseFilterModal from '../components/enjoyHome/AllCourse/AllCourseFilterModal/AllCourseFilterModal';

const AllCoursePage = () => {
  const [modal, setModal] = useState(false);

  // 모달 오픈
  const handleModal = () => {
    setModal(!modal);
  };
  return (
    <AllCoursePageWrapper>
      <EnjoyHeader pageName="" />
      <AllCourse handleModal={handleModal} />
      <Footer />
      {modal ? <AllCourseFilterModal handleModal={handleModal} /> : null}
    </AllCoursePageWrapper>
  );
};

export default AllCoursePage;
