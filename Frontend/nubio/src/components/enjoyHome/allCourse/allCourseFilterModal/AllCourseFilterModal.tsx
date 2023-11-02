import axios from "axios";
import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { setCourseList, setTag } from "@redux/slice/EnjoySlice";
import {
  Modal,
  ModalBody,
  ModalFooter,
  ModalHeader,
  ModalWrapper,
} from "@styles/SAllCoursePage";
import PurposeItem from "../../common/PurposeItem";

type SetAllCourseFilterModalProps = {
  handleModal: () => void;
};

const AllCourseFilterModal: React.FC<SetAllCourseFilterModalProps> = ({
  handleModal,
}) => {
  const dispatch = useDispatch();
  const course_tags = useSelector(
    (state: { enjoy: { course_tag: string[] } }) => state.enjoy.course_tag,
  );
  const purposes = [
    "인생샷 찍기",
    "산책",
    "데이트",
    "맛집탐방",
    "드라이빙",
    "여행",
  ];

  const [selectedPurposes, setSelectedPurposes] = useState<string[]>([]);

  // 목적을 선택할 때 호출되는 함수
  const handlePurposeClick = (purpose: string) => {
    dispatch(setTag(purpose));
    if (!selectedPurposes.includes(purpose)) {
      // 목적이 이미 선택되지 않았다면 추가
      setSelectedPurposes([...selectedPurposes, purpose]);
    } else {
      // 이미 선택된 목적이면 제거
      const updatedPurposes = selectedPurposes.filter(
        (item) => item !== purpose,
      );
      setSelectedPurposes(updatedPurposes);
    }
  };

  const handleSave = async () => {
    await axios
      .post(
        process.env.REACT_APP_SERVER_URL + "/enjoy/v1/enjoy/course/filter",
        { course_tags },
      )
      .then((res) => {
        dispatch(setCourseList(res.data.data.course_list));
        handleModal();
      })
      .catch((err) => {
        console.error(err);
      });
  };
  return (
    <ModalWrapper>
      <Modal>
        <ModalHeader>목적</ModalHeader>
        <ModalBody>
          {purposes.map((purpose: string, index: number) => {
            const isSelected = course_tags.includes(purpose);
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
        <ModalFooter onClick={handleSave}>저장</ModalFooter>
      </Modal>
    </ModalWrapper>
  );
};

export default AllCourseFilterModal;
