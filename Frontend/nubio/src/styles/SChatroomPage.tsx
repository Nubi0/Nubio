import styled from "styled-components";
const MessageList = styled.div`
  padding: 10px;
  height: 100vh; // 적당한 높이 설정
  overflow-y: scroll; // 메시지가 많아지면 스크롤
  background-color: #41d992; // 카카오톡 배경색
`;

const MessageItem = styled.div`
  margin-bottom: 10px;
  padding: 5px;
  border-radius: 15px;
  background-color: #fff;
  max-width: 60%;
  word-wrap: break-word;
`;

const InputField = styled.input`
  width: calc(100% - 60px); // 전송 버튼 너비를 뺀 나머지
  padding: 10px;
  border: none;
  border-radius: 20px;
  margin-right: 10px; // 전송 버튼과의 간격
`;

const SendButton = styled.button`
  width: 50px;
  height: 40px;
  border: none;
  border-radius: 20px;
  background-color: #fee500;
  color: black;
  font-weight: bold;
`;

const ChatInputArea = styled.div`
  display: flex;
  padding: 10px;
  background-color: #fff;
`;

const ChatPageLayout = styled.div`
  display: flex;
  flex-direction: column;
  width: 430px; // 컨테이너 너비에 맞춤
  height: 932px; // 컨테이너 높이에 맞춤
  justify-content: space-between; // 내용은 위로, 입력 영역은 아래로
  background-color: #41d992;
`;

export {
  MessageList,
  MessageItem,
  InputField,
  SendButton,
  ChatInputArea,
  ChatPageLayout,
};
