import { useState } from "react";
import SetPrefrenceModal from "../components/user/prefrence/SetPrefrenceModal";
import SignUpForm from "../components/user/signUp/SignUpForm";
import {
  SignUpWrapper,
  Logo,
  CheckUserWrapper,
  ModalWrapper,
} from "../styles/SSignUpPage";

const SignUpPage = () => {
  // 이미지
  const logoUrl = process.env.PUBLIC_URL + "/assets/nubio.png";
  const modalUrl = process.env.PUBLIC_URL + "/assets/refrence.png";
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
      <Logo src={logoUrl} />
      <ModalWrapper>
        <img src={modalUrl} onClick={openModal} />
        취향 설정
      </ModalWrapper>
      <SignUpForm />
      <CheckUserWrapper>
        <a href="">이미 회원이신가요?</a>
      </CheckUserWrapper>
    </SignUpWrapper>
  );
};

export default SignUpPage;
