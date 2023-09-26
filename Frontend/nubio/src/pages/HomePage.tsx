import { useState } from 'react';
import { HomePageWrapper, ButtonWrapper, Logo } from "../styles/SHomePage";
import SafeButton from "../components/first/SafeButton";
import EnjoyButton from "../components/first/EnjoyButton";
import StartButton from "../components/first/StartButton";
import AppContent from "../components/first/AppContent";
import PreferenceButton from "../components/first/PreferenceButton";
import SetPrefrenceModal from '../components/user/prefrence/SetPrefrenceModal';

const HomePage = () => {
  const logo = process.env.PUBLIC_URL + '/assets/nubio.png';
  const isLogin = localStorage.getItem('refreshToken');
  const [modal, setModal] = useState(false);
  const closeModal = () => {
      setModal(false);
  }
  const openModal = () => {
      setModal(true);
  }
  return (
    <HomePageWrapper>
      <Logo src={logo} />
      <ButtonWrapper>
        <SafeButton />
        <EnjoyButton />
      </ButtonWrapper>
      {isLogin ? <PreferenceButton openModal={openModal} /> : <StartButton />}
      <AppContent />
      {modal && <SetPrefrenceModal closeModal={closeModal} />}
    </HomePageWrapper>
  );
};

export default HomePage;
