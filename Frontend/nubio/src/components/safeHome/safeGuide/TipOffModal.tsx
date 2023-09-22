import { useState } from "react";
import {
  TipOffIcon,
  TipOffWrapper,
  TypeWrapper,
} from "../../../styles/SSafeHomePage";
import useInput from "../../../hooks/useInput";

const TipOffModal = () => {
  const [title, onChangeTitle] = useInput("");
  const loudSpeaker =
    process.env.PUBLIC_URL + "/assets/disaster/loudSpeaker.svg";
  const [isOpen, setIsOpen] = useState(false);
  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
  };
  // 테러타입
  const [reportType, onChangeReportType] = useInput("");
  console.log(reportType);
  console.log(title);
  return (
    <>
      <TipOffIcon src={loudSpeaker} alt="가이드북" onClick={openModal} />
      <h2>제보하기</h2>
      {isOpen ? (
        <TipOffWrapper>
          <h2>제보하기</h2>
          <form>
            <input
              type="text"
              name=""
              id="title"
              value={title}
              onChange={onChangeTitle}
              placeholder="제보 제목을 입력해주세요."
            />
            <textarea
              name=""
              id="content"
              placeholder="제보 내용을 입력해주세요."
            />
            <TypeWrapper>
              <label>
                <input
                  type="radio"
                  name="reportType"
                  value="테러"
                  checked={reportType === "테러"}
                  onChange={onChangeReportType}
                />
                테러
              </label>

              <label>
                <input
                  type="radio"
                  name="reportType"
                  value="사고"
                  checked={reportType === "사고"}
                  onChange={onChangeReportType}
                />
                사고
              </label>

              <label>
                <input
                  type="radio"
                  name="reportType"
                  value="붕괴"
                  checked={reportType === "붕괴"}
                  onChange={onChangeReportType}
                />
                붕괴
              </label>
            </TypeWrapper>
          </form>
          <button onClick={closeModal}>닫기</button>
        </TipOffWrapper>
      ) : null}
    </>
  );
};
export default TipOffModal;
