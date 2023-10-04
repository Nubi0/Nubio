import axios from "axios";
import { useEffect, useState } from "react";
import {
  ReportInfoOverlay,
  ReportInfoWrapper,
  ReportPhotoWrapper,
  ReportInfoButtonWrapper,
} from "../../../styles/SSafeHomePage";
import { Swiper, SwiperSlide } from "swiper/react";
import DeleteReport from "./DeleteReport";
declare global {
  interface Window {
    reportCustomOverlay: any;
  }
}

const Report = () => {
  const [selectedPlace, setSelectedPlace] = useState<any>(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [identificationFlag, setIdentificationFlag] = useState(false);
  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };
  const getReport = () => {
    axios
      .get(`https://nubi0.com/safe/v1/safe/report`)
      .then((res) => {
        console.log(res);
        const places = res.data.data.reportList;
        for (let i = 0; i < places.length; i++) {
          let content = `<div class ="label" style="background:#f9373f; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;">
          <span href="#" class="show-info" data-index="${i}">${places[i].title}</span>
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
              if (places[index].identificationFlag === true) {
                setIdentificationFlag(true);
              }
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
  }, [selectedPlace]);
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
                    )
                  )}
              </Swiper>
            </ReportPhotoWrapper>
            <p>제목: {selectedPlace.title}</p>
            <p>내용: {selectedPlace.content}</p>
            <ReportInfoButtonWrapper className="buttonWrapper">
              {identificationFlag ? (
                <DeleteReport
                  reportId={selectedPlace.reportId}
                  closeModal={closeModal}
                />
              ) : null}
              <button onClick={closeModal}>닫기</button>
            </ReportInfoButtonWrapper>
          </ReportInfoWrapper>
        </ReportInfoOverlay>
      )}
    </>
  );
};

export default Report;
