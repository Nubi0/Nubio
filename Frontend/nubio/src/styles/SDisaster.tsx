import styled from "styled-components";
export const SafeGuideIcon = styled.img`
  width: 4rem;
  height: 4rem;
  z-index: 1;
`; // 재난 대피
export const SafeGuideModalBox = styled.div`
  position: absolute;
  top: 15%;
  background: white;
  width: 80%;
  height: 60%;
  padding: 1rem;
  border-radius: 1rem;
  text-align: center;
  z-index: 2;
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
    cursor: pointer;
  }
`;
// 대피경로
export const DisasterWrapper = styled.div`
  width: 100%;
  height: 90%;
  // background: red;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  div {
    text-align: center;
    display: flex;
    flex-direction: column;
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
