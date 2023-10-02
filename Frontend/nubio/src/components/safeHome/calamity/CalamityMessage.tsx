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
      .post(
        "https://nubi0.com/safe/v1/safe/check",
        {
          longitude: 128.5934,
          latitude: 35.8556,
        },
        // {
        //   window.myLatitude,
        //   window.myLongitude,
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
    // setInterval(getCalamity, 60000);
  }, []);

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
            <button id="safeRoute">대피소 찾기</button>
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
