import styled from "styled-components";
const MessageList = styled.div`
  padding: 10px;
  height: 700px; // 적당한 높이 설정
  overflow-y: scroll; // 메시지가 많아지면 스크롤
  background-color: #f0f0f0; // 카카오톡 배경색
`;

// 개별 메시지를 위한 스타일드 컴포넌트
const MessageItem = styled.div`
  margin-bottom: 10px;
  padding: 5px;
  border-radius: 15px;
  background-color: #fff;
  max-width: 60%;
  word-wrap: break-word;
`;

// 입력 필드를 위한 스타일드 컴포넌트
const InputField = styled.input`
  width: calc(100% - 60px); // 전송 버튼 너비를 뺀 나머지
  padding: 10px;
  border: none;
  border-radius: 20px;
  margin-right: 10px; // 전송 버튼과의 간격
`;

// 전송 버튼을 위한 스타일드 컴포넌트
const SendButton = styled.button`
  width: 50px;
  height: 40px;
  border: none;
  border-radius: 20px;
  background-color: #fee500;
  color: black;
  font-weight: bold;
`;

// 채팅 입력 영역을 위한 컴포넌트
const ChatInputArea = styled.div`
  display: flex;
  padding: 10px;
  background-color: #fff;
`;

// 채팅 페이지 전체 레이아웃을 위한 컴포넌트
const ChatPageLayout = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh; // 전체 화면 높이
  justify-content: space-between; // 내용은 위로, 입력 영역은 아래로
`;

export {
  MessageList,
  MessageItem,
  InputField,
  SendButton,
  ChatInputArea,
  ChatPageLayout,
};
