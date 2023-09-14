import { useEffect } from "react";
import {  SafeHomeWrapper, LogoIcon } from "../styles/SSafeHomePage";
import SearchBar from "../components/common/search/SearchBar";
import Footer from "../components/common/Footer";
import SafeGuideModal from "../components/safeHome/safeGuide/SafeGuideModal";
// import DetailItem from "../components/common/search/detailSearch/DetailItem";

declare global {
  interface Window {
    kakao: any;
  }
}
const SafeHomePage = () => {
  // 이미지
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";

  // 카카오 맵
  useEffect(() => {
    let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
    let options = {
      //지도를 생성할 때 필요한 기본 옵션
      center: new window.kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
      level: 3, //지도의 레벨(확대, 축소 정도)
    };
    let map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
  }, []);

  return (
    <SafeHomeWrapper>
      <SafeGuideModal />
      {/* <MapWrapper id="map" /> */}
      <LogoIcon src={logo} />
      <SearchBar />
      <Footer />
    </SafeHomeWrapper>
  );
};

export default SafeHomePage;
