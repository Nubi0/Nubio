import { SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import Map from "../components/common/map/Map";
import FirstModal from "../components/safeHome/FirstModal";
import Calamity from "../components/safeHome/calamity/Calamity";
import { useNavigate } from "react-router-dom";
import GetReport from "../components/safeHome/report/GetReport";

const SafeHomePage = () => {
  const navigate = useNavigate();
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  return (
    <SafeHomeWrapper>
      <FirstModal />
      <Calamity />
      <GetReport></GetReport>
      <Map />
      <LogoIcon src={logo} onClick={() => navigate("/")} />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
