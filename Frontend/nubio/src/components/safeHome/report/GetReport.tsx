import axios from "axios";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { placements } from "@popperjs/core";
declare global {
  interface Window {
    reportCustomOverlay: any;
  }
}

// interface places{
//   title:string,
//   content:string,
//   fileList:[]
// }
const GetReport = () => {
  const latitude =
    useSelector((state: { map: { latitude: string } }) => state.map.latitude) ||
    null;
  const longitude =
    useSelector(
      (state: { map: { longitude: string } }) => state.map.longitude
    ) || null;
  const [selectedPlace, setSelectedPlace] = useState(null); // 선택된 장소 정보 상태 추가
  console.log(selectedPlace);
  const getReport = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/report?longitude=${longitude}&latitude=${latitude}`
      )
      .then((res) => {
        const places = res.data.data.reportList;
        console.log(places);
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

        const showInfoLinks = document.querySelectorAll(".show-info");
        showInfoLinks.forEach((link) => {
          link.addEventListener("click", (e: any) => {
            e.preventDefault();
            const index = e.target?.getAttribute("data-index");
            if (index !== null) {
              const selectedPlace = places[index];
              setSelectedPlace(selectedPlace);
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
  return (
    <div>
      {/* <p>{selectedPlace?.title}</p> */}
      {/* <p>{selectedPlace?.title}</p> */}
    </div>
  );
};
export default GetReport;
