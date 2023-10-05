import { useSelector } from "react-redux";
import {
  CalamityMessageWrapper,
  CalamityWrapper,
  EvacuationGuideWrapper,
} from "../../../styles/SSafeHomePage";
import { useEffect, useState } from "react";
import axios from "axios";
import { Swiper, SwiperSlide } from "swiper/react";

type EmergencyMessage = {
  city: string;
  county: string;
  message: string;
  md_id: number;
  emer_type: string;
  occurred_time: string;
};
const CalamityMessage = () => {
  const [messageList, setMessageList] = useState<EmergencyMessage[]>([]);
  // 재난문자 수신
  const [isReceiveMessage, setIsReceiveMessage] = useState(false);
  const closeWrapper = () => {
    setIsReceiveMessage(false);
  };

  const getCalamity = () => {
    axios
      .post("https://nubi0.com/safe/v1/safe/check", {
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
      .catch((err) => {
        console.log(err);
      });
  };
  const getNearbyShelter = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/nearwith/safe-shelter/all?longitude=${window.myLongitude}&latitude=${window.myLatitude}&distance=1`
      )
      .then((res) => {
        const shelter = res.data.data.content;
        for (let i = 0; i < shelter.length; i++) {
          let content = `<div class ="label"  style="background:#33ff57; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"></span><span class="center">
              ${shelter[i].name}</span><span class="right"></span></div>`;
          // 커스텀 오버레이가 표시될 위치입니다
          let markerPosition = new kakao.maps.LatLng(
            shelter[i].location.latitude,
            shelter[i].location.longitude
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
        console.log(err);
      });
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
