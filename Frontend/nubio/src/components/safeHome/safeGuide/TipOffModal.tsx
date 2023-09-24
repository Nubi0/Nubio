import { useState, ChangeEvent } from "react";
import {
  TipOffIcon,
  TipOffWrapper,
  TypeWrapper,
  ImageWrapper,
} from "../../../styles/SSafeHomePage";
import useInput from "../../../hooks/useInput";
import axios from "axios";
import Swal from "sweetalert2";
import { useSelector } from "react-redux";

const TipOffModal = () => {
  const loudSpeaker =
    process.env.PUBLIC_URL + "/assets/disaster/loudSpeaker.svg";
  // 모달
  const [isOpen, setIsOpen] = useState(false);
  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
  };
  // 이미지 제외 data
  const [title, onChangeTitle] = useInput("");
  const [content, onChangeContent] = useInput("");
  // const [longitude, setLongitude] = useState("");
  // const [latitude, setLatitude] = useState("");
  const latitude =
    useSelector(
      (state: { kakaoSlice: { latitude: string } }) =>
        state.kakaoSlice.latitude,
    ) || null;
  const longitude =
    useSelector(
      (state: { kakaoSlice: { longitude: string } }) =>
        state.kakaoSlice.longitude,
    ) || null;
  // 이미지
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

    console.log(images);
  };
  // 테러타입
  const [reportType, onChangeReportType] = useInput("");

  const submitForm = (e: any) => {
    e.preventDefault();
    const formData = new FormData();
    const report = {
      title,
      content,
      reportType,
      longitude,
      latitude,
    };
    images.forEach((image, index) => {
      formData.append(`file${index + 1}`, image);
    });
    formData.append(
      "report",
      new Blob([JSON.stringify(report)], { type: "application/json" }),
    );

    axios
      .post("https://nubi0.com/api/v1/safe/report", formData, {
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
            <input
              type="text"
              id="content"
              placeholder="제보 내용을 입력해주세요."
              onChange={onChangeContent}
              value={content}
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
              <label htmlFor="image">제보 사진</label>
              <input type="file" accept="image/*" onChange={onChangeImage} />
            </ImageWrapper>
            <button type="submit" id="submit" onSubmit={submitForm}>
              제보
            </button>
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
