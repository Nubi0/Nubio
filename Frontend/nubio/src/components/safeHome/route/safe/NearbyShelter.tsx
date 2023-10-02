import axios from "axios";
import { useSelector } from "react-redux";
import { useEffect } from "react";

const NearbyShelter = () => {
  const latitude =
    useSelector((state: { map: { latitude: string } }) => state.map.latitude) ||
    null;
  const longitude =
    useSelector(
      (state: { map: { longitude: string } }) => state.map.longitude,
    ) || null;
  const getNearbyShelter = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/nearwith/safe-shelter/all?longitude=${longitude}&latitude=${latitude}&distance=1`,
      )
      .then((res) => {
        console.log(res.data.data.content);
        const shelter = res.data.data.content;
        for (let i = 0; i < shelter.length; i++) {
          let content = `<div class ="label"  style="background:#33ff57; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"></span><span class="center">
              ${shelter[i].name}</span><span class="right"></span></div>`;
          // 커스텀 오버레이가 표시될 위치입니다
          let markerPosition = new kakao.maps.LatLng(
            shelter[i].location.latitude,
            shelter[i].location.longitude,
          );
          // 커스텀 오버레이를 생성합니다
          let customOverlay = new kakao.maps.CustomOverlay({
            position: markerPosition,
            content: content,
          });
          window.safeCustomOverlay = customOverlay;
          // 커스텀 오버레이를 지도에 표시합니다
          window.safeCustomOverlay.setMap(window.map);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    getNearbyShelter();
  });
  return null;
};
export default NearbyShelter;
