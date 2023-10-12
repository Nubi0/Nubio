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
  const accidentIcon =
    process.env.PUBLIC_URL + "/assets/markerIcon/accident.png";
  const constructionIcon =
    process.env.PUBLIC_URL + "/assets/markerIcon/construction.png";
  const terrorIcon = process.env.PUBLIC_URL + "/assets/markerIcon/terror.png";

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
        console.log(places);
        places.forEach((place: any) => {
          let imageSrc = "";

          if (place.reportType === "accident") {
            imageSrc = accidentIcon;
          } else if (place.reportType === "construction") {
            imageSrc = constructionIcon;
          } else if (place.reportType === "terror") {
            imageSrc = terrorIcon;
          }

          const imageSize = new kakao.maps.Size(64, 69);
          const imageOption = { offset: new kakao.maps.Point(27, 69) };
          var markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
            imageOption,
          );
          let markerPosition = new kakao.maps.LatLng(
            place.latitude,
            place.longitude,
          );
          const customOverlaySize = {
            width: `${window.innerWidth * 0.05}px`,
            height: `${window.innerWidth * 0.05}px`,
          };

          var content = `
            <div class="customoverlay" style="width: ${customOverlaySize.width}; height: ${customOverlaySize.height}; zIndex:-1; background: rgba(255, 0, 0, 0.5); border-radius: 50%;"></div>
          `;
          var customOverlay = new kakao.maps.CustomOverlay({
            map: window.map,
            position: markerPosition,
            content: content,
            yAnchor: 1,
          });
          let maker = new kakao.maps.Marker({
            position: markerPosition,
            image: markerImage,
          });
          maker.setMap(window.map);
          kakao.maps.event.addListener(maker, "click", () => {
            setSelectedPlace(place);
            if (place.identificationFlag === true) {
              setIdentificationFlag(true);
            }
            openModal();
          });
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
