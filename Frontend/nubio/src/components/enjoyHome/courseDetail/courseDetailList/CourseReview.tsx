import { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import Rating from '@mui/material/Rating';
import {
  CourseReviewWrapper,
  ReviewHeader,
  ReviewForm,
} from '../../../../styles/SCourseDeatilPage';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import CourseReviewList from './CourseReviewList';

const CourseReview = () => {
  const [value, setValue] = useState<number | null>(0);
  const [reviewList, setReviewList] = useState<reviewProps[]>([]);
  const { courseId } = useParams();
  const [review, setReview] = useState<string>('');

  const handleReview = (e: React.ChangeEvent<HTMLInputElement>) => {
    setReview(e.target.value)
  }

  const handleSubmit = async (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    await axios
      .post(
        process.env.REACT_APP_SERVER_URL + `/enjoy/v1/enjoy/course/review/${courseId}`,
        { point: value, content: review }
      )
      .then((res) => {
        axios
        .get(process.env.REACT_APP_SERVER_URL + `/enjoy/v1/enjoy/course/review/${courseId}`)
        .then((res) => {
            setReviewList(res.data.data.review_list);
            setValue(0);
            setReview('');
          })
          .catch((err) => {
            console.log(err);
          });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    axios
      .get(process.env.REACT_APP_SERVER_URL + `/enjoy/v1/enjoy/course/review/${courseId}`)
      .then((res) => {
        setReviewList(res.data.data.review_list);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <CourseReviewWrapper>
      <ReviewHeader>코스 리뷰</ReviewHeader>
      <ReviewForm>
        <input
          type="text"
          placeholder="이 코스의 후기를 남겨주세요."
          onChange={(e) => handleReview(e)}
          value={review}
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
      <CourseReviewList reviewList={reviewList} setReviewList={setReviewList} />
    </CourseReviewWrapper>
  );
};

export default CourseReview;
