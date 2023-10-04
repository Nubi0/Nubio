import axios from 'axios';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { CourseReviewListWrapper } from '../../../../styles/SCourseDeatilPage';
import CourseReviewItem from './CourseReviewItem';

const CourseReviewList = () => {
  const [reviews, setReviews] = useState<any[]>([]);
  const { courseId } = useParams();

  useEffect(() => {
    axios
      .get(process.env.REACT_APP_SERVER_URL + `/enjoy/v1/enjoy/course/review/${courseId}`)
      .then((res) => {
        setReviews(res.data.data.review_list);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  return (
    <CourseReviewListWrapper>
      {reviews.map((value, index) => {
        return <CourseReviewItem key={index} value={value} />;
      })}
    </CourseReviewListWrapper>
  );
};

export default CourseReviewList;
