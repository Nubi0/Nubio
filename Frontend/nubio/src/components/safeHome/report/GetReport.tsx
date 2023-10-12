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
const GetReport = () => {
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
        const places = res.data.data.reportList;
        places.forEach((place: any) => {
          //   let content = `<div class ="label" style="background:#f9373f; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;">
          //   <span href="#" class="show-info" id="place" >${place.title}</span>
          // </div>`;
          let markerPosition = new kakao.maps.LatLng(
            place.latitude,
            place.longitude,
          );
          let maker = new kakao.maps.Marker({
            position: markerPosition,
          });
          maker.setMap(window.map);
          kakao.maps.event.addListener(maker, "click", () => {
            setSelectedPlace(place);
            if (place.identificationFlag === true) {
              setIdentificationFlag(true);
            }
            openModal();
          });
          // const placeEle = document.getElementById("place");
          // console.log(placeEle);
          // placeEle?.addEventListener("click", () => {
          //   setSelectedPlace(place);
          //   if (place.identificationFlag === true) {
          //     setIdentificationFlag(true);
          //   }
          //   openModal();
          // });
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    getReport();
  }, []);

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

export default GetReport;
