import { useState, ChangeEvent } from "react";
import {
  ReportButton,
  ReportWrapper,
  TypeWrapper,
  ImageWrapper,
  ReportButtonWrapper,
} from "../../../styles/SSafeHomePage";
import useInput from "../../../hooks/useInput";
import axios from "axios";
import Swal from "sweetalert2";

const CreateReport = () => {
  // 모달
  const [isOpen, setIsOpen] = useState(false);
  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
    setImages([]);
    setTitle("");
    setContent("");
    setReportType("");
  };
  // 이미지 제외 data
  const [title, onChangeTitle, setTitle] = useInput("");
  const [content, setContent] = useState("");

  // 이미지
  const reportImage = process.env.PUBLIC_URL + "/assets/reportImage.svg";
  const [images, setImages] = useState<File[]>([]);
  const onChangeImage = (e: ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files;
    if (files) {
      if (images.length + files.length <= 5) {
        const newImages = Array.from(files);
        setImages((prevImages) => [...prevImages, ...newImages]);
      } else {
        Swal.fire({
          title: "이미지는 최대 5개까지만 첨부 가능합니다.",
          text: "NUBIO",
        });
      }
    }
  };
  // 테러타입
  const [reportType, onChangeReportType, setReportType] = useInput("");

  const submitForm = (e: any) => {
    e.preventDefault();
    const formData = new FormData();
    const report = {
      title,
      content,
      reportType,
      longitude: window.myLongitude,
      latitude: window.myLatitude,
    };
    images.forEach((image) => {
      formData.append("file", image);
    });
    formData.append(
      "report",
      new Blob([JSON.stringify(report)], { type: "application/json" }),
    );
    if (
      title == "" ||
      content == "" ||
      reportType == "" ||
      window.myLongitude == "" ||
      window.myLatitude == ""
    ) {
      Swal.fire({
        title: "제보 사진을 제외한 부분은 필수값입니다.",
        text: "NUBIO",
      });
    } else {
      axios
        .post(
          `${process.env.REACT_APP_SERVER_URL}/safe/v1/safe/report`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          },
        )
        .then(() => {
          closeModal();
          Swal.fire({
            title: "제보해주셔서 감사합니다.",
            text: "NUBIO",
          }).then(() => {
            window.location.reload();
          });
        })
        .catch((err) => {
          // console.log(err);
        });
    }
  };
  return (
    <>
      <ReportButtonWrapper>
        <ReportButton onClick={openModal}>제보하기</ReportButton>
      </ReportButtonWrapper>
      {isOpen ? (
        <ReportWrapper>
          <h2>제보하기</h2>
          <form onSubmit={(e) => submitForm(e)}>
            <input
              type="text"
              id="title"
              value={title}
              onChange={onChangeTitle}
              placeholder="제보 제목을 입력해주세요."
            />
            <textarea
              id="content"
              placeholder="제보 내용을 입력해주세요."
              onChange={(e) => setContent(e.target.value)}
              value={content}
              onKeyDown={(e) => {
                if (e.key === "Enter" && !e.shiftKey) {
                  e.preventDefault();
                  setContent((prevContent) => prevContent + "\n");
                }
              }}
            />
            <TypeWrapper>
              <label>
                <input
                  type="radio"
                  name="reportType"
                  value="terror"
                  onChange={onChangeReportType}
                />
                테러
              </label>
              <label>
                <input
                  type="radio"
                  name="reportType"
                  value="accident"
                  onChange={onChangeReportType}
                />
                사고
              </label>
              <label>
                <input
                  type="radio"
                  name="reportType"
                  value="construction"
                  onChange={onChangeReportType}
                />
                붕괴
              </label>
            </TypeWrapper>
            <ImageWrapper>
              <label htmlFor="image">
                제보 사진(최대 5개)
                <img src={reportImage} alt="제보 사진" />
              </label>
              <p>{images.map((image, index) => image.name).join(", ")}</p>
              <input
                type="file"
                id="image"
                accept="image/*"
                onChange={onChangeImage}
              />
            </ImageWrapper>
            <button type="submit" id="submit" onSubmit={submitForm}>
              제보
            </button>
          </form>
          <button id="close" onClick={closeModal}>
            닫기
          </button>
        </ReportWrapper>
      ) : null}
    </>
  );
};
export default CreateReport;
