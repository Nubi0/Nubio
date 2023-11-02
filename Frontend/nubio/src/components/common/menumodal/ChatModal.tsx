import {
  ModalTitle,
  ChatModalWrapper,
  NicknameInput,
  EnterButton,
} from "@styles/SFooter";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const ChatModal = ({ setActive }: { setActive: (value: boolean) => void }) => {
  const Menus = ["채팅 시작"];
  const [nickname, setNickname] = useState("");
  const Navigate = useNavigate();

  const handleNicknameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNickname(e.target.value);
  };

  const handleNicknameSubmit = () => {
    Navigate("/safe/chatroom");
    console.log("Nickname:", nickname);
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

        <h2 style={{ fontSize: "20px" }}>여기는 대구시 채팅방입니다</h2>

        <NicknameInput
          type="text"
          value={nickname}
          onChange={handleNicknameChange}
          placeholder="닉네임을 입력하세요."
        />
        <EnterButton onClick={handleNicknameSubmit}>입장하기</EnterButton>
      </ModalTitle>
    </ChatModalWrapper>
  );
};

export default ChatModal;
