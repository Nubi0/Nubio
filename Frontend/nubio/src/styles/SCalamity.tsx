import styled from "styled-components";
// 재난문자
export const CalamityWrapper = styled.div`
  position: absolute;
  width: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
`;

export const CalamityMessageButton = styled.span`
  cursor: pointer;
  border: 1px solid black;
  width: 80%;
  font-size: 1.5rem;
  text-align: start;
  display: flex;
  justify-content: center;
  margin-bottom: 1rem;
  border-radius: 0.5rem;
`;
export const CalamityMessageWrapper = styled.div`
  width: 100%;
  // margin: auto;
  border: 0.5rem double red;
  background: white;
  text-align: center;
  padding: 10% 0;
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
    cursor: pointer;
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
  border: 1px solid black;
  width: 80%;
  font-size: 1.5rem;
  text-align: start;
  display: flex;
  justify-content: center;
  margin-bottom: 1rem;
  border-radius: 0.5rem;
  cursor: pointer;
`;
