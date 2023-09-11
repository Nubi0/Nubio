import { useState } from 'react';
import { LikeListWrapper } from '../../../styles/SLikePage';
import LikeHeader from './LikeHeader';
import PlaceList from './place/PlaceList';
import CourseList from './course/CourseList';

const LikeList = () => {
  const [active, setActive] = useState({
    place: true,
    course: false,
  });
  const handleButton = (category: string) => {
    if (category === 'place') {
      setActive({
        place: true,
        course: false,
      });
    } else if (category === 'course') {
      setActive({
        place: false,
        course: true,
      });
    }
  };

  return (
    <LikeListWrapper>
      <LikeHeader active={active} handleButton={handleButton} />
      {active.place ? 
        <PlaceList /> :
        <CourseList />
      }
    </LikeListWrapper>
  );
};

export default LikeList;
