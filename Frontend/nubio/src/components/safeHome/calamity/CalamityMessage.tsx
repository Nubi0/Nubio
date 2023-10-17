import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import axios from "axios";
import {
  CalamityMessageWrapper,
  CalamityWrapper,
  EvacuationGuideWrapper,
} from "../../../styles/SSafeHomePage";
import {
  setMessageMarkerList,
  setShowShelters,
} from "../../../redux/slice/SafeSlice";

const CalamityMessage = () => {
  const dispatch = useDispatch();
  const [messageList, setMessageList] = useState<EmergencyMessage[]>([]);
  const [isReceiveMessage, setIsReceiveMessage] = useState(false);
  // 재난문자 모달 닫기
  const closeWrapper = () => {
    setIsReceiveMessage(false);
  };
  // 재난문자 불러오기
  const getCalamity = () => {
    axios
      .post(`${process.env.PUREACT_APP_SERVER_URL}/safe/v1/safe/check`, {
        longitude: window.myLongitude,
        latitude: window.myLatitude,
      })
      .then((res) => {
        if (res.data.data.emergency_message_flag == true) {
          setMessageList(res.data.data.emergency_messages);
          setIsReceiveMessage(true);
        } else {
          setIsReceiveMessage(false);
        }
      })
      .catch(() => {});
  };
  // 가까운 대피소 찾기
  const getNearbyShelter = () => {
    axios
      .get(
        `${process.env.REACT_APP_SERVER_URL}/safe/v1/safe/nearwith/safe-shelter/all?longitude=${window.myLongitude}&latitude=${window.myLatitude}&distance=1`,
      )
      .then((res) => {
        const shelter = res.data.data.content;
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
          window.shelterCustomOverlay = customOverlay;
          window.shelterCustomOverlay.setMap(window.map);
          dispatch(setMessageMarkerList(customOverlay));
        }
        dispatch(setShowShelters(true));
      })
      .catch(() => {});
  };
  useEffect(() => {
    getCalamity();
  }, [window.myLongitude, window.myLatitude]);

  return (
    <>
      {isReceiveMessage ? (
        <CalamityWrapper>
          <CalamityMessageWrapper>
            <p id="title">재난문자{messageList.length}개가 수신되었습니다.</p>
            <Swiper>
              {messageList.map((message, index) => (
                <SwiperSlide key={index}>
                  <EvacuationGuideWrapper>
                    <p id="messageTime">{message.occurred_time}</p>
                    <p id="messageCity">
                      {message.city} {message.county}
                    </p>
                    <p id="messageText">{message.message}</p>
                  </EvacuationGuideWrapper>
                </SwiperSlide>
              ))}
            </Swiper>
            <button id="safeRoute" onClick={getNearbyShelter}>
              대피소 찾기
            </button>
            <button id="close" onClick={closeWrapper}>
              닫기
            </button>
          </CalamityMessageWrapper>
        </CalamityWrapper>
      ) : null}
    </>
  );
};
export default CalamityMessage;
