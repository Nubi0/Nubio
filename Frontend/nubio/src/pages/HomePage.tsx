import { HomePageWrapper, ButtonWrapper } from "../styles/SHomePage";
import SafeButton from "../components/first/SafeButton";
import EnjoyButton from "../components/first/EnjoyButton";
import StartButton from "../components/first/StartButton";
import AppContent from "../components/first/AppContent";

const HomePage = () => {
  return (
    <HomePageWrapper>
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
