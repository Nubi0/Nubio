import styled from "styled-components";

export const SafeHomeWrapper = styled.div`
  width: 100vw;
  height: 100vh;
`;

export const LogoIcon = styled.img`
  position: absolute;
  top: 0px;
  left: 0px;
  width: 4rem;
  height: 2rem;
`;
// 내 위치
export const MyLocation = styled.button`
  position: absolute;
  top: 1rem;
  right: 1rem;
  background: #ffc542;
  border: none;
  border-radius: 1rem;
  padding: 0.3rem;
`;
// 대피 설명서
export const SafeIcon = styled.img`
  position: absolute;
  top: 47rem;
  right: 1rem;
  width: 4rem;
  height: 4rem;
  z-index: 1;
`;

export const SafeGuideIcon = styled.img`
  width: 4rem;
  height: 4rem;
  z-index: 1;
`;
export const ReportIcon = styled.img`
  width: 4rem;
  height: 4rem;
  z-index: 1;
`;

// 사이렌 모달
export const FirstModalOverlay = styled.div`
  z-index: 9998;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  background: rgba(0, 0, 0, 0.7);
`;
export const FirstModalBox = styled.div`
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

// 제보하기
export const ReportModalWrapper = styled.div`
  .swal2-container {
    z-index: 9999; /* 더 높은 값으로 설정 */
  }
`;
export const ReportWrapper = styled.div`
  position: absolute;
  top: 15%;
  background: white;
  width: 80%;
  height: 60%;
  padding: 1.5rem;
  border-radius: 1rem;
  text-align: center;
  z-index: 2;

  #title {
    width: 95%;
    height: 2rem;
    font-size: 1rem;
    margin: 0.5rem 0;
    border-radius: 0.5rem;
    padding: 0.5rem;
  }

  #content {
    width: 95%;
    height: 15rem;
    font-size: 1rem;
    margin: 0.5rem 0;
    border: 0.15rem solid black;
    border-radius: 0.5rem;
    padding: 0.5rem;
  }

  #submit {
    width: 20rem;
    height: 2.5rem;
    font-size: 1.2rem;
    font-weight: 600;
    color: white;
    border: none;
    border-radius: 1rem;
    background-color: #f25260;
    margin-top: 1rem;
  }
`;
export const TypeWrapper = styled.div`
  label {
    font-size: 1.5rem;
    input[type="radio"] {
      width: 20px;
      height: 20px;
      margin-right: 5px;
    }
  }
`;
export const ImageWrapper = styled.div`
  
}
`;

export const CheckReportWrapper = styled.div``;
// 재난 대피
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

// 길 찾기 결과
export const RouteInfoWrapper = styled.div`
  position: absolute;
  bottom: 2.5rem;
  width: 100%;
  height: 15%;
  // background: red;
  display: flex;
  justify-content: space-around;
  z-index: 2;
`;

export const ShortDistanceWrapper = styled.div`
  width: 40%;
  height: 90%;
  background: red;
  border-radius: 1rem;
  padding: 0.5rem;
  p {
    margin: 0.5rem;
    color: white;
  }
`;

export const SafeDistanceWrapper = styled.div`
  width: 40%;
  height: 90%;
  border-radius: 1rem;
  background: #33ff57;
  padding: 0.5rem;
  p {
    margin: 0.5rem;
    color: white;
  }
`;

// 재난문자
export const CalamityWrapper = styled.div`
  // background: red;
  width: 100%;
  height: 30%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1;
`;

export const CalamityMessageWrapper = styled.div`
  background: white;
  width: 80%;
  height: 100%;
  margin: 0 auto;
  border: 0.5rem double red;
  text-align: center;
  p {
    margin: 0.5rem 0;
    margin: 0;
    font-size: 1rem;
  }
  #title {
    font-size: 1.3rem;
    margin-bottom: 1rem;
  }
  button {
    width: 5rem;
    height: 1.5rem;
    font-size: 1.2rem;
    font-weight: 600;
    border: none;
    color: white;
    border-radius: 1rem;
    background-color: red;
  }
`;

// 첫번째 모달 대피경로
export const EvacuationInfoWrapper = styled.div`
  width: 90%;
  height: 30%;
  margin: 1rem auto 0 auto;
`;
export const EvacuationGuideWrapper = styled.div`
  width: 90%;
  height: 45%;
  margin: auto auto 0.5rem auto;
  border-radius: 1rem;
  background: rgb(204, 204, 204, 0.2);
  display: flex;
  padding: 0.5rem;
  #evacuationContent {
    width: 60%;
    height: 90%;
    padding: 1rem;
  }
`;

export const EvacuationIconWrapper = styled.div`
width:30%;
img {
  width: 80%;
  height:80%;
  EvacuationGuideWrapper
}
p{
font-weight:1000;
}
`;
