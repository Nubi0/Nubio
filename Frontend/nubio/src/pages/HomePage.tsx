import { useState, useEffect } from 'react';
import { HomePageWrapper, ButtonWrapper, Logo } from "../styles/SHomePage";
import SafeButton from "../components/first/SafeButton";
import EnjoyButton from "../components/first/EnjoyButton";
import StartButton from "../components/first/StartButton";
import AppContent from "../components/first/AppContent";
import PreferenceButton from "../components/first/PreferenceButton";
import SetPrefrenceModal from '../components/user/prefrence/SetPrefrenceModal';
import axios from 'axios';

const HomePage = () => {
  const logo = process.env.PUBLIC_URL + '/assets/nubio.png';
  const isLogin = localStorage.getItem('refreshToken');
  const [modal, setModal] = useState(false);
  const [isTaste, setIsTaste] = useState(false);
  const closeModal = () => {
      setModal(false);
  }
  const openModal = () => {
      setModal(true);
  }
  useEffect(() => {
    axios.get(process.env.REACT_APP_SERVER_URL + '/enjoy/v1/enjoy/profile/taste')
        .then((res) => {
          setIsTaste(true);
        })
        .catch((err) => {
          if(err.errorCode === 'T-003'){
            setIsTaste(false);
          }
        })
  }, [modal])
  return (
    <HomePageWrapper>
      <Logo src={logo} />
      <ButtonWrapper>
        <SafeButton isLogin={isLogin} />
        <EnjoyButton isTaste={isTaste} isLogin={isLogin} />
      </ButtonWrapper>
      {!isLogin ? <StartButton /> : isTaste ? null : <PreferenceButton openModal={openModal} />}
      <AppContent />
      {modal && <SetPrefrenceModal closeModal={closeModal} />}
    </HomePageWrapper>
  );
};

export default HomePage;
