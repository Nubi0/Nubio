import { useState, useEffect } from 'react';
import { AllCourseWrapper } from '../../../styles/SAllCoursePage';
import AllCourseHeader from './AllCourseHeader';
import AllCourseList from './AllCourseList/AllCourseList';
import Category from './Category';
import { resetCourseTag } from '../../../redux/slice/EnjoySlice';
import { useDispatch } from 'react-redux';

const AllCourse = ({ handleModal }: AllCourseProps) => {
  const [active, setActive] = useState({
    all: true,
    popular: false,
  })
  const dispatch = useDispatch();

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
  useEffect(() => {
    dispatch(resetCourseTag());
  })

  return (
    <AllCourseWrapper>
      <AllCourseHeader handleModal={handleModal} />
      <Category active={active} handleButton={handleButton} />
      <AllCourseList active={active} />
    </AllCourseWrapper>
  );
};

export default AllCourse;
