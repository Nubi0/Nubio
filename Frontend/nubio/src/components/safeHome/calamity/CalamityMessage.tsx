import {
  CalamityMessageWrapper,
  EvacuationGuideWrapper,
  EvacuationInfoWrapper,
  EvacuationIconWrapper,
} from "../../../styles/SSafeHomePage";

const CalamityMessage = ({ setIsReceiveMessage }: any) => {
  const evacuationLogo =
    process.env.PUBLIC_URL + "assets/disaster/evacuation.svg";
  const closeWrapper = () => {
    setIsReceiveMessage(false);
  };

  // 대피경로 안내
  // const;
  return (
    <CalamityMessageWrapper>
      <EvacuationInfoWrapper>
        <p id="title">재난문자가 수신되었습니다.</p>
        <p>2023년 9월 30일 </p>
        <p>00 지역 00 상황 발생</p>
      </EvacuationInfoWrapper>
      <EvacuationGuideWrapper>
        <p id="evacuationContent">
          ~~~~해서 ~~~ 하고, ~~~~해서 ~~~ 하고, ~~~~해서 ~~~ 하고, ~~~~해서 ~~~
          하고, ~~~~해서 ~~~ 하고, ~~~하며 ~~~ 하세요.
        </p>
        <EvacuationIconWrapper>
          <img src={evacuationLogo} alt="대피" />
          <p>대피경로 보기</p>
        </EvacuationIconWrapper>
      </EvacuationGuideWrapper>
      <button onClick={closeWrapper}>닫기</button>
    </CalamityMessageWrapper>
  );
};
export default CalamityMessage;
