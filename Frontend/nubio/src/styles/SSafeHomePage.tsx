import styled from "styled-components";

export const SafeHomeWrapper = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
`;
export const BottomButtonWrapper = styled.div`
  position: absolute;
  width: 100%;
  bottom: 2%;
  z-index: 1;
  text-align: center;
  display: flex;
  justify-content: center;
`;
export const LogoIcon = styled.img`
  position: absolute;
  top: 0.5%;
  left: 0.5%;
  width: 4rem;
  height: 2rem;
`;

// 제보하기 재난 대피
export const OptionButtonWrapper = styled.span`
  position: absolute;
  top: 0.5rem;
  right: 0.5rem;
  color: white;
  padding: 0.4rem;
  border-radius: 1rem;
  background: #ffc542;
  z-index: 1;
`;

// 사이렌 모달
export const OptionModalOverlay = styled.div`
  z-index: 3;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  background: rgba(0, 0, 0, 0.7);
`;
export const OptionModalBox = styled.div`
  background: white;
  width: 80%;
  height: 15%;
  padding: 1rem;
  border-radius: 1rem;
  #close {
    position: relative;
    top: 1rem;
    width: 20rem;
    height: 2.5rem;
    font-size: 1.2rem;
    font-weight: 600;
    border: none;
    border-radius: 1rem;
    background-color: #ffc542;
  }
`;
export const IconWrapper = styled.div`
  display: flex;
  justify-content: space-around;
`;
