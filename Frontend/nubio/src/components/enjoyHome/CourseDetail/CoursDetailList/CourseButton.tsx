import axios from 'axios';
import { CourseButtonWrapper, ButtonDivider } from '../../../../styles/SCourseDeatilPage';
import { useParams } from 'react-router-dom';

const CourseButton = ({ course_info }: { course_info: any }) => {
  const bookMarkUrl = process.env.PUBLIC_URL + '/assets/bigStar.svg';
  const bookMarkFillUrl = process.env.PUBLIC_URL + '/assets/bigStarFill.svg';
  const share = process.env.PUBLIC_URL + '/assets/share.svg';
  const { courseId } = useParams();
  const { favorite_flag } = course_info;
  const handleStar = async () => {
    await axios
      .post(
        process.env.REACT_APP_SERVER_URL + `/enjoy/v1/enjoy/course/favorite/${courseId}`
      )
      .then((res) => {
        console.log(res.data);
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
      <ButtonDivider />
      <div>
        <img src={share} alt="" />
        <button>공유하기</button>
      </div>
    </CourseButtonWrapper>
  );
};

export default CourseButton;
