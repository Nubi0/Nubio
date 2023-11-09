import Footer from "@components/common/Footer";
import EnjoyHeader from "@components/enjoyHome/common/EnjoyHeader";
import LikeList from "@components/user/like/LikeList";
import { LikePageWrapper } from "@styles/SLikePage";

const LikePage = () => {
  return (
    <LikePageWrapper>
      <EnjoyHeader pageName="찜목록" />
      <LikeList />
      <Footer />
    </LikePageWrapper>
  );
};

export default LikePage;
