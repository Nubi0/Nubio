import Recommend from "../components/enjoyHome/Recommend/Recommend";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import Footer from "../components/common/Footer";
import { RecommendPageWrapper } from "../styles/SRecommendPage";

const RecommendPage = () => {
    return( 
        <RecommendPageWrapper>
            <EnjoyHeader pageName="나만의 추천" />
            <Recommend />
            <Footer />
        </RecommendPageWrapper>
    );
}

export default RecommendPage;