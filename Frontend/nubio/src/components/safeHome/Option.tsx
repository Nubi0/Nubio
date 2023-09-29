import { useState } from "react";
import {
  OptionModalBox,
  OptionModalOverlay,
  OptionWrapper,
  IconWrapper,
} from "../../styles/SSafeHomePage";

import CreateReportModal from "./report/CreateReportModal";
import SafeGuideModal from "./disaster/SafeGuideModal";
const Option = () => {
  const [isOpen, setIsOpen] = useState(false);
  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
  };
  return (
    <>
      <OptionWrapper onClick={openModal}>제보/대피</OptionWrapper>
      {isOpen ? (
        <OptionModalOverlay>
          <OptionModalBox>
            <IconWrapper>
              <CreateReportModal />
              <SafeGuideModal />
            </IconWrapper>
            <button id="close" onClick={closeModal}>
              닫기
            </button>
          </OptionModalBox>
        </OptionModalOverlay>
      ) : null}
    </>
  );
};

export default Option;
