import {
  SafeHomeWrapper,
  LogoIcon,
  BottomButtonWrapper,
} from "../styles/SSafeHomePage";
import Map from "../components/common/map/Map";
import CreateReportModal from "../components/safeHome/report/CreateReportModal";
import { useNavigate } from "react-router-dom";

import Shelter from "../components/safeHome/calamity/Shelter";

const SafeHomePage = () => {
  const navigate = useNavigate();
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  return (
    <SafeHomeWrapper>
      <BottomButtonWrapper>
        <Shelter />
        <CreateReportModal />
      </BottomButtonWrapper>
      <Map />
      <LogoIcon src={logo} onClick={() => navigate("/")} />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
