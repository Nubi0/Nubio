import axios from "axios";
import { ShelterButton } from "../../../styles/SSafeHomePage";
import { useState } from "react";

const Shelter = () => {
  const [showShelters, setShowShelters] = useState(false);
  const [markerList, setMarkerList] = useState<any>([]);

  const deleteShelter = () => {
    setShowShelters(false);

    for (let i = 0; i < markerList.length; i++) {
      markerList[i].setMap(null);
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
        const newmarkerList: any = [];
        for (let i = 0; i < shelter.length; i++) {
          let content = `<div class ="label"  style="background:#33ff57; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"></span><span class="center">
              ${shelter[i].name}</span><span class="right"></span></div>`;
          let markerPosition = new kakao.maps.LatLng(
            shelter[i].location.latitude,
            shelter[i].location.longitude,
          );
          let customOverlay = new kakao.maps.CustomOverlay({
            position: markerPosition,
            content: content,
          });
          window.safeCustomOverlay = customOverlay;
          customOverlay.setMap(window.map);
          newmarkerList.push(customOverlay);
        }
        setMarkerList(newmarkerList);
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
