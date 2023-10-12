import {
  CourseDetailListWrapper,
  ItemWrapper,
  DetailHr,
} from '../../../../styles/SCourseDeatilPage';
import CourseDetailItem from './CourseDetailItem';
import CourseReview from './CourseReview';

const CourseDetailList = ({
  place_list,
  course_info,
}: {
  place_list: placeProps[];
  course_info: CourseItem;
}) => {
  return (
    <CourseDetailListWrapper>
      <DetailHr />
      <ItemWrapper>
        {place_list.map((place, index) => {
          return <CourseDetailItem key={index} place={place} />;
        })}
      </ItemWrapper>
      <CourseReview />
    </CourseDetailListWrapper>
  );
};

export default CourseDetailList;
