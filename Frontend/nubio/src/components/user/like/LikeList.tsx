import { useState } from "react";
import { LikeListWrapper } from "@styles/SLikePage";
import LikeHeader from "./LikeHeader";
import CourseList from "./course/CourseList";
import MyCourseList from "./mycourse/MyCourseList";

const LikeList = () => {
  const [active, setActive] = useState({
    place: true,
    course: false,
  });
  const handleButton = (category: string) => {
    if (category === "place") {
      setActive({
        place: true,
        course: false,
      });
    } else if (category === "course") {
      setActive({
        place: false,
        course: true,
      });
    }
  };

  return (
    <LikeListWrapper>
      <LikeHeader active={active} handleButton={handleButton} />
      {active.place ? <MyCourseList /> : <CourseList />}
    </LikeListWrapper>
  );
};

export default LikeList;
