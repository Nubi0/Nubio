import styled from "styled-components";

export const SafeHomeWrapper = styled.div`
  width: 430px;
  height: 932px;
`;

export const MapWrapper = styled.div`
  width: 100%;
  height: 100%;
  z-index: -1;
`;

export const LogoIcon = styled.img`
  position: absolute;
  top: 0px;
  left: 0px;
  width: 4rem;
  height: 2rem;
`;

// 대피 설명서
export const SafeGuideIcon = styled.img`
  position: absolute;
  top: 750px;
  left: 350px;
  width: 4rem;
  height: 4rem;
`;

export const SafeGuideModalOverlay = styled.div`
  z-index: 9998;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  background: rgba(0, 0, 0, 0.7);
`;

export const SafeGuideModalBox = styled.div`
  background: white;
  width: 80%;
  height: 50%;
  padding: 1rem;
  border-radius: 1rem;
  text-align: center;
  button {
    width: 20rem;
    height: 2.5rem;
    font-size: 1.2rem;
    font-weight: 600;
    border: none;
    border-radius: 1rem;
    background-color: #ffc542;
    margin: 1rem;
  }
`;

// 대피경로
export const DisasterWrapper = styled.div`
  width: 100%;
  height: 90%;
  div {
    text-align: center;
    display: flex;
    // flex-direction: column;
    align-items: center;
  }
  p {
    margin: 0;
    width: 3rem;
    font-size: 1.2rem;
    font-weight: 1000;
  }
`;

export const DisasterIcon = styled.img`
  width: 6rem;
  height: 6rem;
  border: solid black 2px;
  border-radius: 2rem;
  padding: 0.3rem;
  margin: 1rem;
`;

export const EvacuationRouteModalBox = styled.div`
  // background: red;
  width: 100%;
  height: 100%;
  border-radius: 1rem;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  p {
    width: 100%;
  }
  button {
    width: 20rem;
    height: 2.5rem;
    font-size: 1.2rem;
    font-weight: 600;
    border: none;
    border-radius: 1rem;
    background-color: #ffc542;
    margin: 1rem;
  }
`;
