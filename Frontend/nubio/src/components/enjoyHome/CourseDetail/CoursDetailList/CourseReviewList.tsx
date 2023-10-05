import { CourseReviewListWrapper } from '../../../../styles/SCourseDeatilPage';
import CourseReviewItem from './CourseReviewItem';

const CourseReviewList = ({reviewList, setReviewList}: {reviewList: any[], setReviewList: any}) => {
  return (
    <CourseReviewListWrapper>
      {reviewList.map((value, index) => {
        return <CourseReviewItem key={index} value={value} setReviewList={setReviewList} />;
      })}
    </CourseReviewListWrapper>
  );
};

export default CourseReviewList;
