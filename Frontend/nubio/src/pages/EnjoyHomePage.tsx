import Header from "../components/common/Header";
import AllButton from "../components/enjoyHome/Home/AllButton";
import Banner from "../components/enjoyHome/Home/Banner";
import CustomCourseList from "../components/enjoyHome/Home/customCourse/CustomCourseList";
import Footer from "../components/common/Footer";
import { EnjoyHomePageWrapper } from "../styles/SEnjoyHomePage";
import CourseMakerIcon from "../components/enjoyHome/Home/CourseMakerIcon";

const EnjoyHomePage = () => {
    return(
      <EnjoyHomePageWrapper>
        <Header />
        <Banner />
        <AllButton />
        <CustomCourseList />
        <Footer />
        <CourseMakerIcon />
      </EnjoyHomePageWrapper>  
    );
}

export default EnjoyHomePage;