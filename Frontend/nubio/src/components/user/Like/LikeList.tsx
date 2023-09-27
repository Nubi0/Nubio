import { useState } from 'react';
import { LikeListWrapper } from '../../../styles/SLikePage';
import LikeHeader from './LikeHeader';
import CourseList from './course/CourseList';
import MineCourseList from './mine/MineCourseList';

const LikeList = () => {
  const [active, setActive] = useState({
    mine: true,
    course: false,
  });
  const handleButton = (category: string) => {
    if (category === 'mine') {
      setActive({
        mine: true,
        course: false,
      });
    } else if (category === 'course') {
      setActive({
        mine: false,
        course: true,
      });
    }
  };

  return (
    <LikeListWrapper>
      <LikeHeader active={active} handleButton={handleButton} />
      {active.mine ? 
        <MineCourseList /> :
        <CourseList />
      }
    </LikeListWrapper>
  );
};

export default LikeList;
