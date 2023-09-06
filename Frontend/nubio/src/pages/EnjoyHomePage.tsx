import Header from "../components/common/Header";
import AllButton from "../components/enjoyHome/Home/AllButton";
import Banner from "../components/enjoyHome/Home/Banner";
import { EnjoyHomePageWrapper } from "../styles/SEnjoyHomePage";

const EnjoyHomePage = () => {
    return(
      <EnjoyHomePageWrapper>
        <Header />
        <Banner />
        <AllButton />
      </EnjoyHomePageWrapper>  
    );
}

export default EnjoyHomePage;