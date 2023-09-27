import axios from "axios";
import { useSelector } from "react-redux";
import { useEffect } from "react";
declare global {
  interface Window {
    reportCustomOverlay: any;
  }
}

const GetReport = () => {
  const latitude =
    useSelector((state: { map: { latitude: string } }) => state.map.latitude) ||
    null;
  const longitude =
    useSelector(
      (state: { map: { longitude: string } }) => state.map.longitude
    ) || null;

  const getReport = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/report?longitude=${longitude}&latitude=${latitude}`
      )
      .then((res) => {
        const places = res.data.data.reportList;
        for (let i = 0; i < places.length; i++) {
          let content = `<div class ="label" style="background:#f9373f; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;">
          <span class="left"></span><span class="center">${places[i].title}</span><span class="right"></span>
          <a href="#" class="show-info" data-index="${i}">상세 정보 보기</a>
        </div>`;
          let markerPosition = new kakao.maps.LatLng(
            places[i].latitude,
            places[i].longitude
          );
          let customOverlay = new kakao.maps.CustomOverlay({
            position: markerPosition,
            content: content,
          });
          window.reportCustomOverlay = customOverlay;
          window.reportCustomOverlay.setMap(window.map);
        }

        // 팝업의 "상세 정보 보기" 링크를 클릭했을 때 이벤트 핸들러 추가
        const showInfoLinks = document.querySelectorAll(".show-info");
        showInfoLinks.forEach((link) => {
          link.addEventListener("click", (e) => {
            e.preventDefault();
            const index = e.target?.getAttribute("data-index");
            if (index !== null) {
              // 선택한 places의 정보를 사용하여 팝업 또는 모달을 열고 정보 표시
              const selectedPlace = places[index];
              // 여기서 팝업 또는 모달을 열고 selectedPlace의 정보를 표시하세요.
              // 예를 들어, state를 사용하여 팝업을 열거나 컴포넌트를 렌더링할 수 있습니다.
            }
          });
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    getReport();
  });
  return null;
};
export default GetReport;
