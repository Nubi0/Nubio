import { useState } from "react";
import {
  TipOffIcon,
  TipOffWrapper,
  TypeWrapper,
} from "../../../styles/SSafeHomePage";
import useInput from "../../../hooks/useInput";
import axios from "axios";
import Swal from "sweetalert2";

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

  const submitForm = (e: any) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("report[title]", title);
    formData.append("report[content]", "테스트입니다");
    formData.append("report[reportType]", reportType);
    formData.append("report[longitude]", "1");
    formData.append("report[latitude]", "1");
    axios
      .post("https://nubi0.com/api/v1/safe", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((res) => {
        console.log(res);
        Swal.fire({
          title: "제보해주셔서 감사합니다.",
          text: "NUBIO",
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <>
      <TipOffIcon src={loudSpeaker} alt="가이드북" onClick={openModal} />
      <h2>제보하기</h2>
      {isOpen ? (
        <TipOffWrapper>
          <h2>제보하기</h2>
          <form onSubmit={(e) => submitForm(e)}>
            <input
              type="text"
              id="title"
              value={title}
              onChange={onChangeTitle}
              placeholder="제보 제목을 입력해주세요."
            />
            <textarea id="content" placeholder="제보 내용을 입력해주세요." />
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
              <button type="submit" id="submit" onSubmit={submitForm}>
                제보
              </button>
            </TypeWrapper>
          </form>
          <button id="close" onClick={closeModal}>
            닫기
          </button>
        </TipOffWrapper>
      ) : null}
    </>
  );
};
export default TipOffModal;
