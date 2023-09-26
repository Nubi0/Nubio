import { useSelector } from 'react-redux';
import {
  AllCourseHeaderWrapper,
  FirstLine,
  SecondLine,
  LeftSide,
  RightSide,
} from '../../../styles/SAllCoursePage';

const AllCourseHeader = ({ handleModal }: AllCourseProps) => {
  const targetImg = process.env.PUBLIC_URL + '/assets/target.svg';
  const filterImg = process.env.PUBLIC_URL + '/assets/filter.svg';
  const region = useSelector((state: any) => state.enjoy.region);
  return (
    <AllCourseHeaderWrapper>
      <LeftSide>
        <FirstLine>
          <img src={targetImg} alt="" />
          <span>{region}</span>
          <span>에서</span>
        </FirstLine>
        <SecondLine>
          <span>전체 코스</span>
          <span>에요</span>
        </SecondLine>
      </LeftSide>
      <RightSide>
        <img src={filterImg} alt="" onClick={handleModal} />
      </RightSide>
    </AllCourseHeaderWrapper>
  );
};

export default AllCourseHeader;
