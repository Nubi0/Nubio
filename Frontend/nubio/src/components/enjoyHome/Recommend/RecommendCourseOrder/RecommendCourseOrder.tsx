import { RecommendCourseOrderWrapper, RecommendCourseTitle } from "../../../../styles/SRecommendPage";
import CourseOrderItem from "./CourseOrderItem";
import CourseItemAddButton from "./CourseItemAddButton";

const RecommendCourseOrder = () => {
    const orderItems = Array.from({ length: 2 }, (_, index) => index + 1);
    return(
        <RecommendCourseOrderWrapper>
            <RecommendCourseTitle>코스 순서 설정</RecommendCourseTitle>
            {orderItems.map((index:number) => {
                return(
                <CourseOrderItem index={index} />
                )
            })}
            <CourseItemAddButton />
        </RecommendCourseOrderWrapper>
    );
};

export default RecommendCourseOrder;