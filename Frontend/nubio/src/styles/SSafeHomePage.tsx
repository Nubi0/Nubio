import styled from "styled-components";

export const SafeHomeWrapper = styled.div`
  width: 100vw;
  height: 100vh;
`;

export const LogoIcon = styled.img`
  position: absolute;
  top: 0.5rem;
  left: 0.5rem;
  width: 4rem;
  height: 2rem;
`;
export const ShelterButton = styled.span`
  position: absolute;
  // top: 0.5rem;
  bottom: 0.5rem;
  right: 10.5rem;
  color: white;
  padding: 0.4rem;
  border-radius: 1rem;
  background: #ffc542;
  z-index: 1;
`;
// 내 위치
export const MyLocation = styled.button`
  position: absolute;
  top: 10rem;
  right: 10rem;
  background: #ffc542;
  border: none;
  border-radius: 1rem;
  padding: 0.3rem;
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
export const SafeGuideIcon = styled.img`
  width: 4rem;
  height: 4rem;
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

// 제보하기
export const ReportButtonWrapper = styled.div`
  position: absolute;
  z-index: 2;
  bottom: 1.5rem;
  right: 1rem;
  color: #f25260;
  padding: 0.4rem;
  border-radius: 1rem;
  background: rgb(255, 255, 255, 80%);
  // margin: 0 auto;
`;
export const ReportButton = styled.span`
  // position: absolute;
  // bottom: 1.5rem;
  // right: 1.5rem;
  // z-index: 1;
`;
export const ReportIcon = styled.img`
  width: 2rem;
  height: 1rem;
  // position: absolute;
  // bottom: 5rem;
  // right: 1rem;
  // width: 3rem;
  // height: 3rem;
  // color: white;
  // padding: 0.4rem;
  // border-radius: 1rem;
  // background: #ffc542;
  // z-index: 1;
`;
export const ReportWrapper = styled.div`
  position: absolute;
  top: 15%;
  background: white;
  width: 80%;
  padding: 1.5rem;
  border-radius: 1rem;
  text-align: center;
  z-index: 2;
  margin: 0 5%;
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
    resize: vertical;
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
text-align:center;
margin-top:1rem;
label{
  font-size:1.5rem;
}
img{
  width:2em;
  height:1.5rem;
  margin-left:0.5rem;
}
  input{
    display:none;
  }
}
`;
export const ReportInfoOverlay = styled.div`
  z-index: 3;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  background: rgba(0, 0, 0, 0.7);
`;
export const ReportInfoWrapper = styled.div`
  width: 20rem;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
  background: white;
  padding: 1rem;
  border-radius: 1rem;
  text-align: center;
  button {
    width: 10rem;
    height: 2.5rem;
    font-size: 1.2rem;
    font-weight: 600;
    border: none;
    border-radius: 1rem;
    background-color: #ffc542;
    margin: 1rem;
  }
`;
export const ReportPhotoWrapper = styled.div`
  width: 100%;
  img {
    width: 100%;
    height: 100%;
  }
`;
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
  bottom: 5rem;
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
  width: 80%;
  height: 100%;
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
