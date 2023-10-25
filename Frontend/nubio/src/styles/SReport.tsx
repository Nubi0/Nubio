import styled from "styled-components";

// 제보하기
// export const ReportButtonWrapper = styled.div`
// z-index: 2;
// color: white;
// padding: 0.4rem;
// border-radius: 1rem;
// background: #f25260;
// `;
export const ReportButton = styled.span`
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
// 제보 생성
export const ReportWrapper = styled.div`
  position: absolute;
  top: -10rem;
  background: white;
  width: 90%;
  padding: 1.5rem;
  border-radius: 1rem;
  border: 0.15rem black solid;
  text-align: center;
  z-index: 2;
  margin: 0 5%;
  #title {
    width: 95%;
    height: 2rem;
    font-size: 1rem;
    margin: 0.5rem 0;
    border: 0.15rem solid black;
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
    width: 90%;
    height: 2.5rem;
    font-size: 1.2rem;
    font-weight: 600;
    color: white;
    border: none;
    border-radius: 1rem;
    background-color: #f25260;
    margin-top: 1rem;
    cursor: pointer;
  }
  #close {
    position: relative;
    top: 1rem;
    width: 90%;
    height: 2.5rem;
    font-size: 1.2rem;
    font-weight: 600;
    border: none;
    border-radius: 1rem;
    background-color: #ffc542;
    cursor: pointer;
  }
`;
// 제보 사진
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
// 제보 종류
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

// 제보 조회

export const ReportInfoOverlay = styled.div`
  z-index: 9999;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
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
  }
`;

export const ReportInfoButtonWrapper = styled.div`
  display: flex;
  button {
    margin: 0.5rem auto;
    cursor: pointer;
  }
`;
