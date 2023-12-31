import {
  ModalTitle,
  MenuList,
  ChatModalWrapper,
  NicknameInput,
  EnterButton,
} from "@styles/SFooter";
import CloseButton from "./CloseButton";
import VerifiedUserIcon from "@mui/icons-material/VerifiedUser";
import { ReactComponent as Nubilogo } from "../../../../public/assets/chat/nubio_logo.svg";
import MenuItem from "./MenuItem";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useDispatch,  useSelector } from 'react-redux';
import { setChatClientInfo,setChatRoomInfo } from "@/redux/slice/LocationSlice";
import styled from 'styled-components';
interface RegionInfoType {
  region_1depth_name: string;
  region_2depth_name: string;
}
const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%; // 폼의 너비를 필요에 따라 조절
`;
const ChatModal = ({ setActive }: { setActive: (value: boolean) => void }) => {
  const Menus = ["채팅 시작"];
  const [nickname, setNickname] = useState("");
  const Navigate = useNavigate();
  const [regionInfo, setRegionInfo] = useState<RegionInfoType | null>(null);
  const [roomId, setRoomId] = useState<number | null>(null);
  const locationState = useSelector((state:any) => state.location);
  const dispatch = useDispatch();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.post(
          process.env.REACT_APP_SERVER_URL +
            "/chatting/v1/chatting/room/location",
          {
            latitude: window.myLatitude,
            longitude: window.myLongitude,
          }
        );
        setRegionInfo(response.data.data.chat_client.region);
        setRoomId(response.data.data.chatting_room.room_id);
        dispatch(setChatClientInfo(response.data.data.chat_client));
        dispatch(setChatRoomInfo(response.data.data.chatting_room));
        // console.log(response.data.data.chatting_room.room_id);
        console.log(locationState);
      } catch (error) {
        console.error("Error during the request:", error);
      }
    };

    fetchData();
  }, []);
  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault(); 
    await handleNicknameSubmit(); 
  };

  const handleNicknameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNickname(e.target.value);
  };

  const handleNicknameSubmit = async () => {
    try {
      console.log("Submitting nickname and trying to enter the room...");

      const response = await axios.post(
        process.env.REACT_APP_SERVER_URL +
          "/chatting/v1/chatting/room/enter/profile",
        {
          room_id: roomId,
          nickname,
        }
      );
      console.log("Response received:", response);
      Navigate(`/safe/chatroom/${roomId}`, { state: { nickname } });

      if (
        response.data &&
        response.data.data.chattingRoomEnterWithProfileReq.room_id != null
      ) {
        const roomId =
          response.data.data.chattingRoomEnterWithProfileReq.room_id;
        console.log("Room ID received:", roomId);
      } else {
        console.error("Invalid room id received from the backend.");
      }
    } catch (error) {
      console.error("Error during the request:", error);
    }
  };

  return (
    <ChatModalWrapper>
      <ModalTitle
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <img
          src={process.env.PUBLIC_URL + "/assets/chat/nubio_logo.svg"}
          alt="Nubio Logo"
        />

        <h2 style={{ fontSize: "20px" }}>
          {regionInfo
            ? `${regionInfo.region_1depth_name} ${regionInfo.region_2depth_name} 채팅방입니다`
            : "위치 정보를 가져오는 중입니다..."}
        </h2>
        <StyledForm onSubmit={handleSubmit}>
        <NicknameInput
          type="text"
          value={nickname}
          onChange={handleNicknameChange}
          placeholder="닉네임을 입력하세요."
        />
        <EnterButton onClick={handleNicknameSubmit}>입장하기</EnterButton>
        </StyledForm>
      </ModalTitle>
    </ChatModalWrapper>
  );
};

export default ChatModal;
