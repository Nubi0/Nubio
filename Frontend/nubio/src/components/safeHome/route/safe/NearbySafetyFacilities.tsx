import axios from "axios";
import { useSelector } from "react-redux";
import { useEffect } from "react";

const NearbySafetyFacilities = () => {
  const startLocation = useSelector(
    (state: { map: { start: StartCoordinates } }) => state.map.start,
  );
  const endLocation = useSelector(
    (state: { map: { end: EndCoordinates } }) => state.map.end,
  );
  const getNearbySafetyFacilities = () => {
    axios
      .post(`${process.env.REACT_APP_SERVER_URL}/safe/v1/safe/route/node`, {
        start_location: {
          longitude: startLocation.x,
          latitude: startLocation.y,
        },
        end_location: {
          longitude: endLocation.x,
          latitude: endLocation.y,
        },
      })
      .then((res) => {
        // console.log(res.data.data.content);
        console.log(res);
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
        // console.log(err);
      });
  };
  useEffect(() => {
    getNearbySafetyFacilities();
  });
  return null;
};
export default NearbySafetyFacilities;
