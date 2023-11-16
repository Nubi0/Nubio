import React from "react";
import styled from "styled-components";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
interface ChatHeaderProps {
  title: string;
  memberCount: number;
}

const HeaderWrapper = styled.div`
  background-color: #fff;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
`;

const Title = styled.h2`
  margin: 0;
  font-size: 20px;
`;

const MemberCount = styled.span`
  background-color: #eee;
  padding: 5px 10px;
  border-radius: 10px;
`;
const BackButton = styled.button`
  background: transparent;
  border: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  margin-right: 10px;
`;

const ChatHeader = () => {
  const locationState = useSelector((state: any) => state.location);
  const navigate = useNavigate();
  const { room_id } = locationState.chattingRoom;
  const handleBack = async () => {
    try {
      // const response = await axios.post(
      //   process.env.REACT_APP_SERVER_URL + "/chatting/v1/chatting/room/out",
      //   {
      //     room_id: room_id,
      //   }
      // );

      navigate("/safe");
    } catch (error) {
      console.log("error!!", error);
    }
  };

  return (
    <HeaderWrapper>
      <Title>{locationState.chattingRoom.sgg_name}</Title>
      <BackButton onClick={handleBack}>
        <FontAwesomeIcon icon={faArrowLeft} />
      </BackButton>
    </HeaderWrapper>
  );
};

export default ChatHeader;
