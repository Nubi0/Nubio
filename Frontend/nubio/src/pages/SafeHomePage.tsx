import { SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import Footer from "../components/common/Footer";
import SafeGuideModal from "../components/safeHome/safeGuide/SafeGuideModal";
import Map from "../components/common/map/Map";

declare global {
  interface Window {
    kakao: any;
  }
}

const SafeHomePage = () => {
  // 이미지
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  const siren = process.env.PUBLIC_URL + "/assets/disaster/siren.png";
  const loudSpeaker =
    process.env.PUBLIC_URL + "/assets/disaster/loudSpeaker.png";
  // 검색 redux
  return (
    <SafeHomeWrapper>
      <SafeGuideModal />
      <Map />
      <LogoIcon src={siren} />
      <Footer />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
