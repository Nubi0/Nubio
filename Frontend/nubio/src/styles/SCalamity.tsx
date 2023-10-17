import styled from "styled-components";
// 재난문자
export const CalamityWrapper = styled.div`
  width: 100%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
  padding: 2rem 0;
`;

export const CalamityMessageWrapper = styled.div`
  width: 80%;
  margin: auto;
  border: 0.5rem double red;
  background: white;
  text-align: center;
  p {
    margin: 0.5rem 0;
    margin: 0;
    font-size: 1rem;
  }
  #title {
    font-size: 1.5rem;
    margin: 1rem;
    color: red;
  }
  button {
    font-size: 1.2rem;
    font-weight: 600;
    border: none;
    color: white;
    border-radius: 1rem;
    margin-left: 1rem;
    margin-bottom: 1rem;
    padding: 0.2rem 1rem;
  }
  #close {
    background-color: black;
  }
  #safeRoute {
    background-color: red;
  }
`;

export const EvacuationGuideWrapper = styled.div`
  width: 80%;
  margin: auto auto 0.5rem auto;
  border-radius: 1rem;
  background: rgb(204, 204, 204, 0.2);
  text-align: center;
  padding: 1rem;
  #messageTime,
  #messageCity {
    font-size: 1.2rem;
    margin: 0.3rem 0;
  }
  #messageText {
    margin-top: 0.5rem;
  }
`;

// 대피소
export const ShelterButton = styled.span`
  color: white;
  padding: 0.4rem;
  border-radius: 1rem;
  background: #f25260;
  z-index: 1;
  cursor: pointer;
`;
