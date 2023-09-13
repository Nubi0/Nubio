import styled from 'styled-components';

export const CourseSelectPageWrapper = styled.div`
  position: relative;
`;

export const CourseSelectWrapper = styled.div`
  height: calc(904px - 5.4rem);
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
  top: 0.25rem;
  right: 1rem;
  border: none;
  background-color: transparent;
  font-size: 1rem;
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