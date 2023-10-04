import { CourseReviewItemWrapper } from '../../../../styles/SCourseDeatilPage';
import Box from '@mui/material/Box';
import Rating from '@mui/material/Rating';
import axios from 'axios';
import { useParams } from 'react-router';

const CourseReviewItem = ({ value, setReviewList }: { value: any, setReviewList: any }) => {
  const { content, point, nickname, course_reivew_id } = value;
  const { courseId } = useParams();
  const deleteButton = async () => {
    await axios
      .delete(
        process.env.REACT_APP_SERVER_URL +
          `/enjoy/v1/enjoy/course/review/${course_reivew_id}`
      )
      .then((res) => {
        axios.get(process.env.REACT_APP_SERVER_URL + `/enjoy/v1/enjoy/course/review/${courseId}`)
              .then((res) => {
                setReviewList(res.data.data.review_list);
              })
              .catch((err) => {
                console.log(err);
              })
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
