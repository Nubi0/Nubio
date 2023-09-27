import { LikeHeaderWrapper, Place, Course } from '../../../styles/SLikePage';

type likeProps = {
  active: {
    mine: boolean;
    course: boolean;
  },
  handleButton: (category: string) => void;
}

const LikeHeader = ({active, handleButton}: likeProps) => {
  return (
    <LikeHeaderWrapper>
      <Place
        onClick={() => handleButton('mine')}
        className={active.mine ? 'active' : ''}
      >
        내 코스
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
