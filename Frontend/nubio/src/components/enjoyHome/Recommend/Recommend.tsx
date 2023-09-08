import { RecommendWrapper } from "../../../styles/SRecommendPage";
import RecommendLocation from "./RecommendLocation";
import RecommendCourse from "./RecommendCourseOrder/RecommendCourseOrder";

const Recommend = () => {
    return(
        <RecommendWrapper>
            <RecommendLocation />
            <RecommendCourse />
            <hr />
        </RecommendWrapper>
    );
};

export default Recommend;