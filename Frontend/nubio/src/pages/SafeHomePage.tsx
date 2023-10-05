import {
  SafeHomeWrapper,
  LogoIcon,
  BottomButtonWrapper,
} from "../styles/SSafeHomePage";
import Map from "../components/common/map/Map";
import CreateReport from "../components/safeHome/report/CreateReport";
import { useNavigate } from "react-router-dom";

import Shelter from "../components/safeHome/calamity/Shelter";

const SafeHomePage = () => {
  const navigate = useNavigate();
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  return (
    <SafeHomeWrapper>
      <Map />
      <BottomButtonWrapper>
        <Shelter />
        <CreateReport />
      </BottomButtonWrapper>
      <LogoIcon src={logo} onClick={() => navigate("/")} />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
