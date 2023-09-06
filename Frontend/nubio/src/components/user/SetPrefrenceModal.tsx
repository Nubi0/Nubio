import React, { useState } from "react";
import {
  PrefrenceModalOverlay,
  PrefrenceModalBox,
  PrefrenceModalWrapper,
} from "../../styles/SSignUpPage";

const SetPrefrenceModal = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  return (
    <PrefrenceModalWrapper>
      <button onClick={openModal}>취 향 설 정</button>
      {isModalOpen && (
        <PrefrenceModalOverlay>
          <PrefrenceModalBox>
            <h2>Modal Title</h2>
            <p>Modal Content Goes Here</p>
            <button onClick={closeModal}>Close Modal</button>
          </PrefrenceModalBox>
        </PrefrenceModalOverlay>
      )}
    </PrefrenceModalWrapper>
  );
};

export default SetPrefrenceModal;
