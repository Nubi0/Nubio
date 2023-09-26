import { SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import Map from "../components/common/map/Map";
import FirstModal from "../components/safeHome/FirstModal";
import Calamity from "../components/safeHome/calamity/Calamity";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import GetCalamity from "../components/safeHome/calamity/GetCalamity";

declare global {
  interface Window {
    kakao: any;
  }
}

const SafeHomePage = () => {
  const navigate = useNavigate();
  // 이미지
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";

  return (
    <SafeHomeWrapper>
      <FirstModal />
      <Calamity />
      <Map />
      <LogoIcon src={logo} onClick={() => navigate("/")} />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
