import { useState } from 'react';
import { CourseSelectModalWrapper, Modal, ModalTitle, ModalBody, ItemWrapper, ModalFooter } from "../../../../styles/SCourseSelectPage";
import PurposeItem from "../../common/PurposeItem";
import axios from 'axios';
import useInput from '../../../../hooks/useInput';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import Swal from 'sweetalert2';

const CourseSelectModal = ({setModal}: any) => {
    const [selectedPurposes, setSelectedPurposes] = useState<string[]>([]);
    const purposes = ['드라이빙', '인생샷 찍기', '산책', '데이트', '맛집탐방', '음주가무', '여행']
    const [title, setTitle] = useInput('');
    const positions = useSelector((state: {enjoy: {positions: placeItem[]}}) => state.enjoy.positions);
    const navigate = useNavigate();
    const location = useSelector((state: {enjoy: {location: string}}) => state.enjoy.location);

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
    const handleSave = async () => {
        const post_purpose = selectedPurposes.map((value) => {
            return { value };
        });
        const positionData = positions.map((value: any) => {
            console.log(value);
            return {kakao_id: value.id, sequence: value.index}
        })
        console.log(positionData);
        const config = {
            title,
            content: title,
            course_tags: post_purpose,
            pubilc_flag: true,
            region: location,
            place_list: positionData,
        }
        await axios.post('https://nubi0.com/enjoy/v1/enjoy/course', config)
                .then((res) => {
                    setModal();
                    Swal.fire({
                        title: '코스 저장 완료',
                        icon: 'success',
                        text: '저장한 코스는 프로필-내 코스 에서 확인하실 수 있습니다.'
                    })
                    .then(() =>
                        navigate('/enjoy')
                    )
                    
                })
                .catch((err) => {
                console.error(err);
                })
    };
    return(
        <CourseSelectModalWrapper>
            <Modal>
                <ModalTitle>
                    <div>코스 제목을 입력해주세요</div>
                    <input type="text" placeholder='ex) 바람개비 나들이' onChange={setTitle} />
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
                    <button onClick={handleSave}>저장</button>
                </ModalFooter>
            </Modal>
        </CourseSelectModalWrapper>
    );
};

export default CourseSelectModal;