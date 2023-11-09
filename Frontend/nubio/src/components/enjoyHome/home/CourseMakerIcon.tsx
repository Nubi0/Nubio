import { useNavigate } from "react-router-dom";
import { CourseMakerIconWrapper } from "@styles/SEnjoyHomePage";

const CourseMakerIcon = () => {
  const navigate = useNavigate();
  const coursemaker = process.env.PUBLIC_URL + "/assets/course/coursemaker.svg";
  return (
    <CourseMakerIconWrapper
      onClick={() => {
        navigate("/mycourse");
      }}
    >
      <img src={coursemaker} alt="" />
    </CourseMakerIconWrapper>
  );
};

export default CourseMakerIcon;
