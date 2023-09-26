import { useSelector } from "react-redux";
import {
  CalamityMessageWrapper,
  EvacuationGuideWrapper,
} from "../../../styles/SSafeHomePage";
import { useEffect, useState } from "react";
import axios from "axios";
import { Swiper, SwiperSlide } from "swiper/react";
import Navigation from "swiper";

type EmergencyMessage = {
  city: string;
  county: string;
  message: string;
  md_id: number;
  emer_type: string;
};
const CalamityMessage = () => {
  const [messageList, setMessageList] = useState<EmergencyMessage[]>([]);
  const evacuationLogo =
    process.env.PUBLIC_URL + "assets/disaster/evacuation.svg";
  // 재난문자 수신
  const [isReceiveMessage, setIsReceiveMessage] = useState(false);
  const closeWrapper = () => {
    setIsReceiveMessage(false);
  };

  const latitude =
    useSelector((state: { map: { latitude: string } }) => state.map.latitude) ||
    null;
  const longitude =
    useSelector(
      (state: { map: { longitude: string } }) => state.map.longitude
    ) || null;
  const getCalamity = () => {
    axios
      .post(
        "https://nubi0.com/safe/v1/safe/check",
        {
          longitude: 128.5934,
          latitude: 35.8556,
        }
        // {
        //   latitude,
        //   longitude,
        // }
      )
      .then((res) => {
        console.log(res);
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

  useEffect(() => {
    getCalamity();
    setInterval(getCalamity, 60000);
  }, []);
  // 시간
  const time = new Date();
  const year = time.getFullYear();
  const month = String(time.getMonth() + 1).padStart(2, "0");
  const day = String(time.getDate()).padStart(2, "0");
  const hours = String(time.getHours()).padStart(2, "0");
  const minutes = String(time.getMinutes()).padStart(2, "0");

  const formattedTime = `${year}년 ${month}월 ${day}일 ${hours}:${minutes}`;
  const test = {
    code: 200,
    status: "OK",
    data: {
      emergency_messages: [
        {
          city: "대구광역시",
          county: "ㅁㄴㅇ",
          message: "ㅁㄴㅇ",
          md_id: 123,
          emer_type: "지진",
          emer_stage: "안전안내",
        },
      ],
      emergency_message_flag: true,
    },
  };
  return (
    <>
      {isReceiveMessage ? (
        <CalamityMessageWrapper>
          <p id="title">재난문자{messageList.length}개가 수신되었습니다.</p>
          <Swiper>
            {messageList.map((message) => (
              <SwiperSlide key={message.md_id}>
                <EvacuationGuideWrapper>
                  <p id="messageTime">{formattedTime}</p>
                  <p id="messageCity">
                    {message.city} {message.county}
                  </p>
                  <p id="messageText">{message.message}</p>
                </EvacuationGuideWrapper>
              </SwiperSlide>
            ))}
          </Swiper>
          <button id="safeRoute">대피경로</button>
          <button id="close" onClick={closeWrapper}>
            닫기
          </button>
        </CalamityMessageWrapper>
      ) : null}
    </>
  );
};
export default CalamityMessage;
