import { useState } from 'react';
import { CourseSelectModalWrapper, Modal, ModalTitle, ModalBody, ItemWrapper, ModalFooter } from "../../../../styles/SCourseSelectPage";
import PurposeItem from "../../common/PurposeItem";

const CourseSelectModal = ({setModal}: any) => {
    const [selectedPurposes, setSelectedPurposes] = useState<string[]>([]);
    const purposes = ['드라이빙', '인생샷 찍기', '산책', '데이트', '맛집탐방', '음주가무', '여행']
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
    return(
        <CourseSelectModalWrapper>
            <Modal>
                <ModalTitle>
                    <div>코스 제목을 입력해주세요</div>
                    <input type="text" placeholder='ex) 바람개비 나들이' />
                </ModalTitle>
                <ModalBody>
                <div>
                    목적
                </div>
                <ItemWrapper>
                    {purposes.map((purpose, idx) => {
                        const isSelected = selectedPurposes.includes(purpose);
                        return(
                            <PurposeItem key={idx} purpose={purpose} handleClick={() => handlePurposeClick(purpose)} isSelected={isSelected}/>
                        )
                    })}
                </ItemWrapper>
                </ModalBody>
                <ModalFooter>
                    <button onClick={setModal}>취소</button>
                    <hr />
                    <button onClick={setModal}>저장</button>
                </ModalFooter>
            </Modal>
        </CourseSelectModalWrapper>
    );
};

export default CourseSelectModal;