import { AllCourseWrapper } from '../../../styles/SAllCoursePage';
import AllCourseHeader from './AllCourseHeader';
import AllCourseList from './AllCourseList/AllCourseList';
import Category from './Category';

const AllCourse = ({ handleModal }: AllCourseProps) => {
  return (
    <AllCourseWrapper>
      <AllCourseHeader handleModal={handleModal} />
      <Category />
      <AllCourseList />
    </AllCourseWrapper>
  );
};

export default AllCourse;
