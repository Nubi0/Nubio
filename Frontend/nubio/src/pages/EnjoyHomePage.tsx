import Header from "../components/common/Header";
import AllButton from "../components/enjoyHome/Home/AllButton";
import { EnjoyHomePageWrapper } from "../styles/SEnjoyHomePage";

const EnjoyHomePage = () => {
    return(
      <EnjoyHomePageWrapper>
        <Header />
        <AllButton />
      </EnjoyHomePageWrapper>  
    );
}

export default EnjoyHomePage;