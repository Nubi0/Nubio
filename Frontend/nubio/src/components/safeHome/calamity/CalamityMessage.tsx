import { CalamityMessageWrapper } from "../../../styles/SSafeHomePage";

const CalamityMessage = ({ setIsReceiveMessage }: any) => {
  const closeWrapper = () => {
    setIsReceiveMessage(false);
  };
  return (
    <CalamityMessageWrapper>
      <p>재난문자가 수신되었습니다.</p>
      <button onClick={closeWrapper}>닫기</button>
    </CalamityMessageWrapper>
  );
};
export default CalamityMessage;
