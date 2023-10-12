import { CourseReviewListWrapper } from '../../../../styles/SCourseDeatilPage';
import CourseReviewItem from './CourseReviewItem';

const CourseReviewList = ({reviewList, setReviewList}: {reviewList: reviewProps[], setReviewList: React.Dispatch<React.SetStateAction<reviewProps[]>> }) => {
  return (
    <CourseReviewListWrapper>
      {reviewList.map((value, index) => {
        return <CourseReviewItem key={index} value={value} setReviewList={setReviewList} />;
      })}
    </CourseReviewListWrapper>
  );
};

export default CourseReviewList;
