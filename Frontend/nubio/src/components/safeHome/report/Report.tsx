import axios from "axios";
import { useEffect, useState } from "react";
import {
  ReportInfoOverlay,
  ReportInfoWrapper,
  ReportPhotoWrapper,
} from "../../../styles/SSafeHomePage";
import { Swiper, SwiperSlide } from "swiper/react";
declare global {
  interface Window {
    reportCustomOverlay: any;
  }
}

const GetReport = () => {
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
        `https://nubi0.com/safe/v1/safe/report?longitude=${window.myLongitude}&latitude=${window.myLatitude}`,
      )
      .then((res) => {
        console.log(res);
        const places = res.data.data.reportList;
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
            <ReportPhotoWrapper>
              <Swiper>
                {selectedPlace.fileList.fileUrls &&
                  selectedPlace.fileList.fileUrls.map(
                    (fileUrl: string, index: string) => (
                      <SwiperSlide key={index}>
                        <img src={fileUrl} alt="사진" />
                      </SwiperSlide>
                    ),
                  )}
              </Swiper>
            </ReportPhotoWrapper>
            <p>제목: {selectedPlace.title}</p>
            <p>내용: {selectedPlace.content}</p>
            <button onClick={closeModal}>닫기</button>
          </ReportInfoWrapper>
        </ReportInfoOverlay>
      )}
    </>
  );
};

export default GetReport;
