import { SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import Map from "../components/common/map/Map";
import FirstModal from "../components/safeHome/FirstModal";
import Calamity from "../components/safeHome/calamity/Calamity";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

declare global {
  interface Window {
    kakao: any;
  }
}

const SafeHomePage = () => {
  const navigate = useNavigate();
  // 이미지
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  // 재난문자 수신
  const [isReceiveMessage, setIsReceiveMessage] = useState(true);
  return (
    <SafeHomeWrapper>
      <FirstModal />
      {isReceiveMessage ? (
        <Calamity setIsReceiveMessage={setIsReceiveMessage} />
      ) : null}
      {/* <Calamity setIsReceiveMessage={setIsReceiveMessage} /> */}
      <Map />
      <LogoIcon src={logo} onClick={() => navigate("/")} />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
