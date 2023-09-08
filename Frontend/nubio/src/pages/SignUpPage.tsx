import { useState } from "react";
import SetPrefrenceModal from "../components/user/prefrence/SetPrefrenceModal";
import SignUpForm from "../components/user/signUp/SignUpForm";
import {
  SignUpWrapper,
  Logo,
  CheckUserWrapper,
  ModalWrapper,
} from "../styles/SSignUpPage";
import { Link } from "react-router-dom";

const SignUpPage = () => {
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
      <Link to="/">
        <Logo src={logoUrl} />
      </Link>
      <ModalWrapper>
        <img src={modalUrl} onClick={openModal} />

        <h2>취향 설정</h2>
      </ModalWrapper>
      <SignUpForm />
      <CheckUserWrapper>
        <a href="">이미 회원이신가요?</a>
      </CheckUserWrapper>
    </SignUpWrapper>
  );
};

export default SignUpPage;
