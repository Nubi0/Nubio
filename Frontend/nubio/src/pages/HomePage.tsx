import { HomePageWrapper } from "../styles/SHomePage";
import SafeButton from '../components/first/SafeButton';
import EnjoyButton from "../components/first/EnjoyButton";

const HomePage = () => {
    return(
        <HomePageWrapper>
            <SafeButton />
            <EnjoyButton />
        </HomePageWrapper>
    );
}

export default HomePage;