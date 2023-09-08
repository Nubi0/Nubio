import { useState } from "react";
import {
  ProfileWrapper,
  MyInfoWrapper,
  SettingWrapper,
  LogOutWrapper,
  DeleteUserWrapper,
  MyListWrapper,
  MyRefrenceWrapper,
} from "../styles/SProfilePage";
// import Swal from "sweetalert2";
import SetPrefrenceModal from "../components/user/prefrence/SetPrefrenceModal";
import Footer from "../components/common/Footer";

const ProfilePage = () => {
  const user = process.env.PUBLIC_URL + "/assets/user.png";
  // 취향설정 모달
  const modalUrl = process.env.PUBLIC_URL + "/assets/refrence.png";
  const [isModalOpen, setIsModalOpen] = useState(false);
  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
  };
  // 로그아웃 클릭 시
  // const Swal = require("sweetalert2");

  const openLogOut = () => {
    // Swal.fire("Hello, SweetAlert2!");
  };
  // 회원 탈퇴 클릭시
  const openDeleteUser = () => {};
  return (
    <ProfileWrapper>
      {isModalOpen ? <SetPrefrenceModal closeModal={closeModal} /> : null}
      <MyInfoWrapper>
        <h1>내 정보</h1>
        <img src={user} alt="" />
        <h2>이메일</h2>
        <h2>이름</h2>
        <h2>닉네임</h2>
      </MyInfoWrapper>
      <SettingWrapper>
        <MyRefrenceWrapper>
          <h1 onClick={openModal}>나의 취향</h1>
        </MyRefrenceWrapper>
        <hr />
        <MyListWrapper>
          <h1>나의 찜 목록</h1>
        </MyListWrapper>
        <hr />
        <LogOutWrapper onClick={openLogOut}>
          <h1>로그아웃</h1>
        </LogOutWrapper>
        <hr />
        <DeleteUserWrapper onClick={openDeleteUser}>
          <h1>회원탈퇴</h1>
        </DeleteUserWrapper>
      </SettingWrapper>
      <Footer />
    </ProfileWrapper>
  );
};

export default ProfilePage;
