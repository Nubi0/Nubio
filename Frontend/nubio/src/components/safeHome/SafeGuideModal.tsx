import { useState } from "react";
import {
  SafeGuideModalOverlay,
  SafeGuideModalBox,
  SafeGuideIcon,
} from "../../styles/SSafeHomePage";
import Disaster from "./Disaster";

const SafeGuideModal = () => {
  //이미지
  const safeGuide = process.env.PUBLIC_URL + "/assets/safeGuide.png";

  const [isOpen, setIsOpen] = useState(false);
  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
  };
  return (
    <>
      <SafeGuideIcon src={safeGuide} alt="가이드북" onClick={openModal} />
      {isOpen ? (
        <SafeGuideModalOverlay>
          <SafeGuideModalBox>
            <Disaster />
            <button onClick={closeModal}>닫기</button>
          </SafeGuideModalBox>
        </SafeGuideModalOverlay>
      ) : null}
    </>
  );
};

export default SafeGuideModal;
