import { HomePageWrapper, ButtonWrapper } from "../styles/SHomePage";
import SafeButton from "../components/first/SafeButton";
import EnjoyButton from "../components/first/EnjoyButton";
import StartButton from "../components/first/StartButton";
import AppContent from "../components/first/AppContent";

const HomePage = () => {
  const logo = process.env.PUBLIC_URL + '/assets/nubio.png';
  return (
    <HomePageWrapper>
      <img src={logo} />
      <ButtonWrapper>
        <SafeButton />
        <EnjoyButton />
      </ButtonWrapper>
      <StartButton />
      <AppContent />
    </HomePageWrapper>
  );
};

export default HomePage;
