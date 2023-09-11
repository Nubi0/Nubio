import { RecommendWrapper } from "../../../styles/SRecommendPage";
import RecommendLocation from "./RecommendLocation";
import RecommendCourse from "./RecommendCourseOrder/RecommendCourseOrder";
import RecommendPurpose from "./RecommendPurpose";
import RecommendButton from "./RecommendButton";

const Recommend = () => {
    return(
        <RecommendWrapper>
            <RecommendLocation />
            <RecommendCourse />
            <hr />
            <RecommendPurpose />
            <RecommendButton />
        </RecommendWrapper>
    );
};

export default Recommend;