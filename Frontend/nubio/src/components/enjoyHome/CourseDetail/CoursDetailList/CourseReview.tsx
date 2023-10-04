import { useState } from 'react';
import Box from '@mui/material/Box';
import Rating from '@mui/material/Rating';
import {
  CourseReviewWrapper,
  ReviewHeader,
  ReviewForm,
} from '../../../../styles/SCourseDeatilPage';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import useInput from '../../../../hooks/useInput';
import CourseReviewList from './CourseReviewList';

const CourseReview = () => {
  const [value, setValue] = useState<number | null>(0);
  const { courseId } = useParams();
  const [review, onReview] = useInput('');
  const handleSubmit = async (e: any) => {
    e.preventDefault();
    await axios
      .post(
        process.env.REACT_APP_SERVER_URL + `/enjoy/v1/enjoy/course/review/${courseId}`,
        { point: value, content: review }
      )
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <CourseReviewWrapper>
      <ReviewHeader>코스 리뷰</ReviewHeader>
      <ReviewForm>
        <input
          type="text"
          placeholder="이 코스의 후기를 남겨주세요."
          onChange={onReview}
        />
        <Box>
          <Rating
            value={value}
            onChange={(event, newValue) => {
              setValue(newValue);
            }}
          />
        </Box>
        <button onClick={(e) => handleSubmit(e)}>작성</button>
      </ReviewForm>
      <hr />
      <br />
      <CourseReviewList />
    </CourseReviewWrapper>
  );
};

export default CourseReview;
