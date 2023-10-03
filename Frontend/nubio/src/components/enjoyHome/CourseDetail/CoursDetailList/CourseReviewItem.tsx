import { CourseReviewItemWrapper } from '../../../../styles/SCourseDeatilPage';
import Box from '@mui/material/Box';
import Rating from '@mui/material/Rating';
import axios from 'axios';

const CourseReviewItem = ({ value }: { value: any }) => {
  const { content, point, nickname, course_reivew_id } = value;

  const deleteButton = async () => {
    await axios
      .delete(
        process.env.REACT_APP_SERVER_URL +
          `/enjoy/v1/enjoy/course/review/${course_reivew_id}`
      )
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <CourseReviewItemWrapper>
      <div>
        <span>{nickname}</span>
        <Box>
          <Rating value={point} readOnly />
        </Box>
      </div>
      <div>
        <span>{content}</span>
        <button onClick={deleteButton}>삭제</button>
      </div>
    </CourseReviewItemWrapper>
  );
};

export default CourseReviewItem;
