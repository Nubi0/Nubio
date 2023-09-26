import { SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import Footer from "../components/common/Footer";
import SafeGuideModal from "../components/safeHome/safeGuide/SafeGuideModal";
import Map from "../components/common/map/Map";
import FirstModal from "../components/safeHome/safeGuide/FirstModal";

declare global {
  interface Window {
    kakao: any;
  }
}

const SafeHomePage = () => {
  // 이미지
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";

  // 검색 redux
  return (
    <SafeHomeWrapper>
      <FirstModal />
      <Map />
      <LogoIcon src={logo} />
      {/* <Footer /> */}
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
