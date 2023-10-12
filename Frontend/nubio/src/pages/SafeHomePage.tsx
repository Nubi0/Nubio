import {
  SafeHomeWrapper,
  LogoIcon,
  BottomButtonWrapper,
} from "../styles/SSafeHomePage";
import Map from "../components/common/map/Map";
import CreateReport from "../components/safeHome/report/CreateReport";
import { useNavigate } from "react-router-dom";
import Shelter from "../components/safeHome/calamity/Shelter";
import GetReport from "../components/safeHome/report/GetReport";

const SafeHomePage = () => {
  const navigate = useNavigate();
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";

  return (
    <SafeHomeWrapper>
      <GetReport />
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
