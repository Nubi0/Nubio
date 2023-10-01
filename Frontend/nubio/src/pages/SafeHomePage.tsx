import { SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import Map from "../components/common/map/Map";
import Option from "../components/safeHome/Option";
import { useNavigate } from "react-router-dom";
import GetReport from "../components/safeHome/report/GetReport";
import Footer from "../components/common/Footer";
import CalamityMessage from "../components/safeHome/calamity/CalamityMessage";
// import NearbyShelter from "../components/safeHome/route/safe/NearbyShelter";
import NearbySafetyFacilities from "../components/safeHome/route/safe/NearbySafetyFacilities";

const SafeHomePage = () => {
  const navigate = useNavigate();
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  return (
    <SafeHomeWrapper>
      <Option />
      <CalamityMessage />
      <GetReport />
      {/* <NearbyShelter /> */}
      <NearbySafetyFacilities />
      <Map />
      <LogoIcon src={logo} onClick={() => navigate("/")} />
      <Footer />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
