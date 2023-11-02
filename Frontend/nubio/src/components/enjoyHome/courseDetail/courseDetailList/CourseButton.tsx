import axios from "axios";
import { CourseButtonWrapper } from "@styles/SCourseDeatilPage";
import { useParams } from "react-router-dom";

const CourseButton = ({
  course_info,
  setPlaceList,
  setCourseInfo,
}: {
  course_info: courseInfo;
  setPlaceList: React.Dispatch<React.SetStateAction<placeProps[]>>;
  setCourseInfo: React.Dispatch<React.SetStateAction<courseInfo>>;
}) => {
  const bookMarkUrl =
    process.env.PUBLIC_URL + "/assets/course/courseDetail/star.svg";
  const bookMarkFillUrl =
    process.env.PUBLIC_URL + "/assets/course/courseDetail/starFill.svg";
  const { courseId } = useParams();
  const { favorite_flag } = course_info;
  console.log(favorite_flag);
  const handleStar = async () => {
    await axios
      .post(
        process.env.REACT_APP_SERVER_URL +
          `/enjoy/v1/enjoy/course/favorite/${courseId}`,
      )
      .then((res) => {
        axios
          .get(
            process.env.REACT_APP_SERVER_URL +
              `/enjoy/v1/enjoy/course/${courseId}`,
          )
          .then((res) => {
            setPlaceList(res.data.data.place_list);
            setCourseInfo(res.data.data.course_info);
          })
          .catch((err) => {
            console.error(err);
          });
      })
      .catch((err) => {
        console.error(err);
      });
  };

  return (
    <CourseButtonWrapper>
      <div onClick={handleStar}>
        <img src={favorite_flag ? bookMarkFillUrl : bookMarkUrl} alt="" />
        <button>찜하기</button>
      </div>
    </CourseButtonWrapper>
  );
};

export default CourseButton;
