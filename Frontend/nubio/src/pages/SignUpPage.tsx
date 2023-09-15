import { useState } from "react";
import SetPrefrenceModal from "../components/user/prefrence/SetPrefrenceModal";
import SignUpForm from "../components/user/signUp/SignUpForm";
import {
  SignUpWrapper,
  SignUpLogo,
  CheckUserWrapper,
  ModalWrapper,
} from "../styles/SSignUpPage";
import { Link, useNavigate } from "react-router-dom";

const SignUpPage = () => {
  const navigate = useNavigate();

  // 이미지
  const logoUrl = process.env.PUBLIC_URL + "/assets/nubio.png";
  const modalUrl = process.env.PUBLIC_URL + "/assets/refrence.png";
  // 취향 설정 모달
  const [isModalOpen, setIsModalOpen] = useState(false);
  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
  };
  return (
    <SignUpWrapper>
      {isModalOpen ? <SetPrefrenceModal closeModal={closeModal} /> : null}

      <SignUpLogo src={logoUrl} onClick={() => navigate("/")} />

      <ModalWrapper>
        <img src={modalUrl} onClick={openModal} />
        <h2>취향 설정</h2>
      </ModalWrapper>
      <SignUpForm />
      <CheckUserWrapper>
        <a onClick={() => navigate("/login")}>이미 회원이신가요?</a>
      </CheckUserWrapper>
    </SignUpWrapper>
  );
};

export default SignUpPage;
