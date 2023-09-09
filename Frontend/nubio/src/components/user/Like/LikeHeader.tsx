import { useState } from 'react';
import { LikeHeaderWrapper, Place, Course } from '../../../styles/SLikePage';

const LikeHeader = () => {
  const [active, setActive] = useState({
    place: false,
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
    <LikeHeaderWrapper>
      <Place
        onClick={() => handleButton('place')}
        className={active.place ? 'active' : ''}
      >
        장소
      </Place>
      <Course
        onClick={() => handleButton('course')}
        className={active.course ? 'active' : ''}
      >
        코스
      </Course>
    </LikeHeaderWrapper>
  );
};

export default LikeHeader;
