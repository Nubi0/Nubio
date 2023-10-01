import { SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import Map from "../components/common/map/Map";
import CreateReportModal from "../components/safeHome/report/CreateReportModal";
import { useNavigate } from "react-router-dom";
import GetReport from "../components/safeHome/report/GetReport";
import Footer from "../components/common/Footer";
import CalamityMessage from "../components/safeHome/calamity/CalamityMessage";

import Shelter from "../components/safeHome/calamity/Shelter";

const SafeHomePage = () => {
  const navigate = useNavigate();
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  return (
    <SafeHomeWrapper>
      <CalamityMessage />
      <GetReport />
      <Shelter />
      <CreateReportModal />
      <Map />
      <LogoIcon src={logo} onClick={() => navigate("/")} />
      {/* <Footer /> */}
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
