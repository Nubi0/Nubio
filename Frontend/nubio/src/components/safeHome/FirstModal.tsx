import { useState } from "react";
import {
  FirstModalBox,
  FirstModalOverlay,
  SafeIcon,
  IconWrapper,
} from "../../styles/SSafeHomePage";

import ReportModal from "./report/ReportModal";
import SafeGuideModal from "./disaster/SafeGuideModal";
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
              <ReportModal />
              <SafeGuideModal />
            </IconWrapper>
            <button id="close" onClick={closeModal}>
              닫기
            </button>
          </FirstModalBox>
        </FirstModalOverlay>
      ) : null}
    </>
  );
};

export default FirstModal;
