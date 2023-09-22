import { useState } from "react";
import {
  FirstModalBox,
  FirstModalOverlay,
  SafeIcon,
  IconWrapper,
} from "../../../styles/SSafeHomePage";
import SafeGuideModal from "./SafeGuideModal";
import TipOffModal from "./TipOffModal";
const FirstModal = () => {
  const siren = process.env.PUBLIC_URL + "/assets/disaster/siren.png";
  const [isOpen, setIsOpen] = useState(false);
  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
  };
  return (
    <>
      <SafeIcon src={siren} alt="가이드북" onClick={openModal} />
      {isOpen ? (
        <FirstModalOverlay>
          <FirstModalBox>
            <IconWrapper>
              <TipOffModal />
              <SafeGuideModal />
            </IconWrapper>
            <button onClick={closeModal}>닫기</button>
          </FirstModalBox>
        </FirstModalOverlay>
      ) : null}
    </>
  );
};

export default FirstModal;
