import styled from 'styled-components';

export const CourseSelectPageWrapper = styled.div`
  position: relative;
  height: 100%;
`;

export const CourseSelectWrapper = styled.div`
  height: 100%;
  .custom-marker {
    width: 3rem;
    height: 3rem;
    background-color: #fff; /* 배경색 설정 (원형 마커를 원하는 색상으로 설정하세요) */
    border-radius: 50%; /* 원형 모양으로 설정 */
    text-align: center;
    line-height: 24px;
    color: #333; /* 텍스트 색상 설정 */
    font-size: 14px; /* 텍스트 크기 설정 */
  }
`;

export const MapWrapper = styled.div`
  width: 100%;
  height: calc(904px - 5.4rem);
  z-index: 0;
`;

export const CourseMaker = styled.button`
  position: absolute;
  top: 1rem;
  right: 1rem;
  border: none;
  background-color: transparent;
  font-size: 1rem;
`;

export const ModalOpen = styled.button`
  position: absolute;
  top: 1rem;
  right: 50%;
`;

export const CoursePinWrapper = styled.div`
  margin-right: 0.5rem;
  img{
    width: 3rem;
    border: 1px solid black;
    height: 3rem;
    border-radius: 50%;
  }
`;

export const PinWrapper = styled.div`
display: flex;
  position: absolute;
  top: 7rem;
  left: 2rem;
`;

export const CourseSelectModalWrapper = styled.div`
  position: absolute;
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100vh;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 3;
`;
export const Modal = styled.div`
  background-color: white;
  width: 90%;
  border-radius: 0.5rem;
`;

export const ModalTitle = styled.div`
  margin-bottom: 0.5rem;
  margin-top: 0.5rem;
  input{
    margin-top: 0.8rem;
    border: none;
    width: 100%;
    font-size: 2rem;
  }
  padding: 0.5rem;
`;

export const ModalBody = styled.div`
  margin-bottom: 1rem;
  padding: 0.5rem;
`;

export const ItemWrapper = styled.div`
  display: flex;
`;

export const ModalFooter = styled.div`
  border-top: 1px solid black;
  display: flex;
  justify-content: space-between;
  button{
    background-color: transparent;
    border: none;
    width: 100%;
    padding: 0.5rem;
    font-size: 1rem;
  }
  hr{
  }
`;
