import axios from "axios";
import { ShelterButton } from "../../../styles/SSafeHomePage";
import { useState } from "react";

const Shelter = () => {
  const [showShelters, setShowShelters] = useState(false);
  const [markerList, setMarkerList] = useState([]);
  const [markerObjects, setMarkerObjects] = useState<any>([]); // 마커 객체를 관리하는 상태

  const deleteShelter = () => {
    setShowShelters(false);

    // 마커 객체를 이용하여 화면에 표시된 마커를 숨깁니다.
    for (let i = 0; i < markerObjects.length; i++) {
      markerObjects[i].setMap(null);
    }
  };

  const getNearbyShelter = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/nearwith/safe-shelter/all?longitude=${window.myLongitude}&latitude=${window.myLatitude}&distance=1`,
      )
      .then((res) => {
        console.log(res.data.data.content);
        setShowShelters(true);
        const shelter = res.data.data.content;
        setMarkerList(shelter);

        // 마커 객체를 생성하고 관리할 배열을 초기화합니다.
        const newMarkerObjects: any = [];

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
          customOverlay.setMap(window.map);

          // 생성한 마커 객체를 배열에 추가합니다.
          newMarkerObjects.push(customOverlay);
        }

        // 마커 객체 배열을 상태로 업데이트합니다.
        setMarkerObjects(newMarkerObjects);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      {showShelters ? (
        <ShelterButton onClick={deleteShelter}>대피소 가리기</ShelterButton>
      ) : (
        <ShelterButton onClick={getNearbyShelter}>대피소 찾기</ShelterButton>
      )}
    </>
  );
};

export default Shelter;
