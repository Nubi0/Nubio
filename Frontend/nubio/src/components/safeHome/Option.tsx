import React, { useState } from "react";
import {
  OptionModalBox,
  OptionModalOverlay,
  OptionButtonWrapper,
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
      <OptionButtonWrapper onClick={openModal}>제보/대피</OptionButtonWrapper>
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
