import { useState } from "react";
import { ProfilePageWrapper } from "../styles/SProfilePage";
import SetPrefrenceModal from "../components/user/prefrence/SetPrefrenceModal";
import Footer from "../components/common/Footer";
import EnjoyHeader from "../components/enjoyHome/common/EnjoyHeader";
import Profile from "../components/user/Profile";

const ProfilePage = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const openModal = () => {
    setIsModalOpen(true);
  };
  const closeModal = () => {
    setIsModalOpen(false);
  };
  return (
    <ProfilePageWrapper>
      {isModalOpen ? <SetPrefrenceModal closeModal={closeModal} /> : null}
      <EnjoyHeader pageName="마이페이지" />
      <Profile openModal={openModal} />
      <Footer />
    </ProfilePageWrapper>
  );
};

export default ProfilePage;
