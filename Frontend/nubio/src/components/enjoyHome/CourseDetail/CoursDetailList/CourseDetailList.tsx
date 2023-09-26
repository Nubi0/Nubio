import {
  CourseDetailListWrapper,
  ItemWrapper,
  DetailHr,
} from '../../../../styles/SCourseDeatilPage';
import CourseButton from './CourseButton';
import CourseDetailItem from './CourseDetailItem';
import { useEffect } from 'react';

const CourseDetailList = ({
  place_list,
  course_info,
}: {
  place_list: any[];
  course_info: object;
}) => {
  return (
    <CourseDetailListWrapper>
      <DetailHr />
      <ItemWrapper>
        {place_list.map((place, index) => {
          return <CourseDetailItem key={index} place={place} />;
        })}
      </ItemWrapper>
      <CourseButton course_info={course_info} />
    </CourseDetailListWrapper>
  );
};

export default CourseDetailList;
