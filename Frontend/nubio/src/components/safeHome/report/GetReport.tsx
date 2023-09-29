import axios from "axios";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { placements } from "@popperjs/core";
import {
  ReportInfoOverlay,
  ReportInfoWrapper,
} from "../../../styles/SSafeHomePage";
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
      (state: { map: { longitude: string } }) => state.map.longitude,
    ) || null;
  const [selectedPlace, setSelectedPlace] = useState<any>(null); // 선택된 장소 정보 상태 추가
  const [modalOpen, setModalOpen] = useState(false); // 모달 상태 추가

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };
  console.log(selectedPlace);
  const getReport = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/report?longitude=${longitude}&latitude=${latitude}`,
      )
      .then((res) => {
        const places = res.data.data.reportList;
        console.log(places);
        for (let i = 0; i < places.length; i++) {
          let content = `<div class ="label" style="background:#f9373f; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;">
          <span href="#" class="show-info" data-index="${i}">${places[i].title}</span>
        </div>`;
          let markerPosition = new kakao.maps.LatLng(
            places[i].latitude,
            places[i].longitude,
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
              openModal();
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
    <>
      {selectedPlace && modalOpen && (
        <ReportInfoOverlay>
          <ReportInfoWrapper>
            <p>제목 : {selectedPlace.title}</p>
            <p>내용 : {selectedPlace.content}</p>
            <button onClick={closeModal}>닫기</button>
          </ReportInfoWrapper>
        </ReportInfoOverlay>
      )}
    </>
  );
};

export default GetReport;
