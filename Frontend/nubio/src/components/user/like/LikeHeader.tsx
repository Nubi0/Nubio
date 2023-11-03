import { LikeHeaderWrapper, Place, Course } from "@styles/SLikePage";

type likeProps = {
  active: {
    place: boolean;
    course: boolean;
  };
  handleButton: (category: string) => void;
};

const LikeHeader = ({ active, handleButton }: likeProps) => {
  return (
    <LikeHeaderWrapper>
      <Place
        onClick={() => handleButton("place")}
        className={active.place ? "active" : ""}
      >
        내가 만든 코스
      </Place>
      <Course
        onClick={() => handleButton("course")}
        className={active.course ? "active" : ""}
      >
        내가 찜한 코스
      </Course>
    </LikeHeaderWrapper>
  );
};

export default LikeHeader;
