import Header from "../components/common/Header";
import AllButton from "../components/enjoyHome/Home/AllButton";
import Banner from "../components/enjoyHome/Home/Banner";
import CustomCourseList from "../components/enjoyHome/Home/customCourse/CustomCourseList";
import Footer from "../components/common/Footer";
import { EnjoyHomePageWrapper } from "../styles/SEnjoyHomePage";

const EnjoyHomePage = () => {
    return(
      <EnjoyHomePageWrapper>
        <Header />
        <Banner />
        <AllButton />
        <CustomCourseList />
        <Footer />
      </EnjoyHomePageWrapper>  
    );
}

export default EnjoyHomePage;