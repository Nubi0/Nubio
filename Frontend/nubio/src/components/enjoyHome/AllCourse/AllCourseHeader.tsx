import { AllCourseHeaderWrapper, FirstLine, SecondLine, LeftSide, RightSide } from "../../../styles/SAllCoursePage";

const AllCourseHeader = () => {
    const targetImg = process.env.PUBLIC_URL + '/assets/target.svg';
    const filterImg = process.env.PUBLIC_URL + '/assets/filter.svg';
    return(
        <AllCourseHeaderWrapper>
            <LeftSide>
                <FirstLine>
                    <img src={targetImg} alt="" />
                    <span>주소주소 주소주소</span><span>에서</span>
                </FirstLine>
                <SecondLine>
                    <span>전체 코스</span>
                    <span>에요</span>
                </SecondLine>
            </LeftSide>
            <RightSide>
                <img src={filterImg} alt="" />
            </RightSide>
        </AllCourseHeaderWrapper>
    );
}

export default AllCourseHeader;