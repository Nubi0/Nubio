import { useDispatch } from "react-redux";
import { useEffect, useRef, useState } from "react";
import Swal from "sweetalert2";

import GetReport from "@components/safeHome/report/GetReport";
import { MapWrapper } from "@styles/SMap";
import CalamityMessageHome from "@components/safeHome/calamity/CalamityMessageHome";
import { setListIsOpen } from "@redux/slice/MapSlice";
import Search from "../search/Search";

// 카카오맵 관련
const { kakao } = window as any;
declare global {
  interface Window {
    kakaoManager: any;
    map: any;
    infowindow: any;
    ps: any;
    polyline: any;
    startCustomOverlay: any;
    endCustomOverlay: any;
    safeCustomOverlay: any;
    myLatitude: any;
    myLongitude: any;
    shelterCustomOverlay: any;
    searchMarkers: any;
  }
}

const KakaoMap = ({ position }: { position: placeItem[] }) => {
  const mapRef = useRef(null);
  const dispatch = useDispatch();
  const [calamityMessageVisible, setCalamityMessageVisible] = useState(false);
  const [reportIsOpen, setReportIsOpen] = useState(false);
  // 현재위치
  const startCurPosition = () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          window.myLatitude = position.coords.latitude;
          window.myLongitude = position.coords.longitude;
          window.map.setCenter(
            new window.kakao.maps.LatLng(window.myLatitude, window.myLongitude),
          );
          const marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(
              window.myLatitude,
              window.myLongitude,
            ),
          });
          marker.setMap(window.map);
        },
        (error) => {
          Swal.fire({
            title: `geolocation ${error} 발생.`,
            text: "NUBIO",
          });
        },
      );
    } else {
      Swal.fire({
        title: "지금 브라우저에서는 geolocation를 지원하지 않습니다.",
        text: "NUBIO",
      });
    }
  };

  // 검색어가 바뀔 때마다 재렌더링되도록 useEffect 사용
  useEffect(() => {
    startCurPosition();
    const mapContainer = document.getElementById("map");
    const mapOption = {
      center: new kakao.maps.LatLng(0, 0),
      level: 3, // 지도의 확대 레벨
    };
    const map = new kakao.maps.Map(mapContainer, mapOption);
    mapRef.current = map;
    window.map = map;
    const ps = new kakao.maps.services.Places();
    window.ps = ps;
    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    window.infowindow = infowindow;
    window.map.addListener("click", () => {
      dispatch(setListIsOpen(false));
    });
    setCalamityMessageVisible(true);
    setReportIsOpen(true);
  }, [window.myLatitude, window.myLongitude]);
  return (
    <>
      <MapWrapper id="map" className="map" />
      <Search />
      {/* {reportIsOpen && <GetReport />} */}
      <GetReport />
      {calamityMessageVisible && <CalamityMessageHome />}
    </>
  );
};

export default KakaoMap;
