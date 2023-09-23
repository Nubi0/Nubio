import { LikeHeaderWrapper, Place, Course } from '../../../styles/SLikePage';

type likeProps = {
  active: {
    place: boolean;
    course: boolean;
  },
  handleButton: (category: string) => void;
}

const LikeHeader = ({active, handleButton}: likeProps) => {
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
