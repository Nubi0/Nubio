import { useState } from 'react';
import { AllCourseWrapper } from '../../../styles/SAllCoursePage';
import AllCourseHeader from './AllCourseHeader';
import AllCourseList from './AllCourseList/AllCourseList';
import Category from './Category';

const AllCourse = ({ handleModal }: AllCourseProps) => {
  const [active, setActive] = useState({
    all: false,
    popular: false,
  })
  const handleButton = (category: string) => {
      if(category === 'all'){
          setActive({
              all: true,
              popular: false,
          })
      } else if(category === 'popular') {
          setActive({
              all: false,
              popular: true,
          })
      }
  }
  return (
    <AllCourseWrapper>
      <AllCourseHeader handleModal={handleModal} />
      <Category active={active} handleButton={handleButton} />
      <AllCourseList active={active} />
    </AllCourseWrapper>
  );
};

export default AllCourse;
