import { useState, useEffect } from "react";
import { ProfilePageWrapper, SaveButton } from "../styles/SProfilePage";
import SetPrefrenceModal from "../components/user/prefrence/SetPrefrenceModal";
import Footer from "../components/common/Footer";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import Profile from "../components/user/Profile";
import Swal from "sweetalert2";
import { useDispatch, useSelector } from "react-redux";
import { setIsChange, setIsInputDisabled, setNewNickName } from "../redux/slice/Profileslice";

const ProfilePage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const isChange = useSelector((state: any) => state.profile.isChange);
  const newNickName = useSelector((state: any) => state.profile.newNickName);
  const dispatch = useDispatch();

  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
  };

  const save = () => {
    Swal.fire({
      position: "center",
      title: "변경사항을 \n저장하시겠습니까?",
      text: "NUBIO",
      showConfirmButton: true,
      showCancelButton: true,
      confirmButtonText: "예",
      cancelButtonText: "아니요",
      color: "black",
  }).then((res) => {
      dispatch(setIsChange(false));
      dispatch(setNewNickName(newNickName))
  })
  }

  useEffect(() => {
    dispatch(setIsChange(false));
    dispatch(setIsInputDisabled(true));
  }, [])

  return (
    <ProfilePageWrapper>
      {isModalOpen ? <SetPrefrenceModal closeModal={closeModal} /> : null}
      <EnjoyHeader pageName="마이페이지" />
      <Profile openModal={openModal} />
      {isChange && <SaveButton onClick={save}>저장</SaveButton>}
      <Footer />
    </ProfilePageWrapper>
  );
};

export default ProfilePage;
