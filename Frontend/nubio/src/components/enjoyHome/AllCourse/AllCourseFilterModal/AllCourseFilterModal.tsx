import React, { useState } from 'react';
import {
  ModalWrapper,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
} from '../../../../styles/SAllCoursePage';
import PurposeItem from './PurposeItem';

type SetAllCourseFilterModalProps = {
  handleModal: () => void;
};

const AllCourseFilterModal: React.FC<SetAllCourseFilterModalProps> = ({
  handleModal,
}) => {
  const purposes = [
    '인생샷 찍기',
    '산책',
    '데이트',
    '맛집탐방',
    '드라이빙',
    '여행',
    '인생샷 찍기',
    '산책',
    '데이트',
    '맛집탐방',
    '드라이빙',
    '여행',
    '인생샷 찍기',
    '산책',
    '데이트',
    '맛집탐방',
    '드라이빙',
    '여행',
    '인생샷 찍기',
    '산책',
    '데이트',
    '맛집탐방',
    '드라이빙',
    '여행',
    '인생샷 찍기',
    '산책',
    '데이트',
    '맛집탐방',
    '드라이빙',
    '여행',
    '인생샷 찍기',
    '산책',
    '데이트',
    '맛집탐방',
    '드라이빙',
    '여행',
    '인생샷 찍기',
    '산책',
    '데이트',
    '맛집탐방',
  ];

  const [selectedPurposes, setSelectedPurposes] = useState<string[]>([]);

  // 목적을 선택할 때 호출되는 함수
  const handlePurposeClick = (purpose: string) => {
    if (!selectedPurposes.includes(purpose)) {
      // 목적이 이미 선택되지 않았다면 추가
      setSelectedPurposes([...selectedPurposes, purpose]);
    } else {
      // 이미 선택된 목적이면 제거
      const updatedPurposes = selectedPurposes.filter((item) => item !== purpose);
      setSelectedPurposes(updatedPurposes);
    }
  };

  return (
    <ModalWrapper>
      <Modal>
        <ModalHeader>목적</ModalHeader>
        <ModalBody>
          {purposes.map((purpose: string, index: number) => {
            const isSelected = selectedPurposes.includes(purpose);
            return (
              <PurposeItem
                purpose={purpose}
                key={index}
                handleClick={() => handlePurposeClick(purpose)}
                isSelected={isSelected}
              />
            );
          })}
        </ModalBody>
        <ModalFooter onClick={handleModal}>저장</ModalFooter>
      </Modal>
    </ModalWrapper>
  );
};

export default AllCourseFilterModal;
