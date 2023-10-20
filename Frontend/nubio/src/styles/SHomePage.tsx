import styled from "styled-components";
import "animate.css";

// 첫 홈페이지
export const HomePageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 430px;
  height: 932px;
  position: relative;
  text-align: center;
`;

export const Logo = styled.img`
  width: 20rem;
  margin: 3rem 3rem 2rem 4rem;
`;

export const ButtonWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  background-color: #41d992;
  padding: 1rem;
  border-radius: 1rem;
  margin: 0 2rem;
`;

// 첫 홈페이지 안전 버튼
export const SafeButtonWrapper = styled.div`
  background-color: #f25260;
  width: 10rem;
  height: 12rem;
  border-radius: 1rem;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  button {
    border: none;
    background-color: transparent;
    font-size: 2rem;
    font-weight: bold;
    color: white;
  }
  img {
    width: 7rem;
    height: 7rem;
    padding: 1rem;
  }
`;

// 첫 페이지 흥미 버튼
export const EnjoyButtonWrapper = styled.div`
  background-color: #ffc542;
  width: 10rem;
  height: 12rem;
  border-radius: 1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  button {
    border: none;
    background-color: transparent;
    font-size: 2rem;
    font-weight: bold;
    color: white;
  }
  img {
    width: 7rem;
    height: 7rem;
    padding: 1rem;
  }
`;

// 첫 홈페이지 시작하기 버튼
export const StartButtonWrapper = styled.div`
  background-color: #41d992;
  height: 5rem;
  border-radius: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 2rem 2rem 5rem 2rem;
  button {
    border: none;
    background-color: transparent;
    font-size: 2.5rem;
    font-weight: bold;
    color: white;
  }
`;
// 시작하기 위 멘트
export const OnTheStartWrapper = styled.div`
  display: flex;
  position: relative;
  top: 2rem;
  margin-left: 4rem;
  span {
    font-size: 1.5rem;
  }
  #firstText {
    // color: #41d992;
    p {
      transform: rotate(-5deg);
    }
  }
  #secondText {
    margin-left: 0.5rem;
    color: #ffc542;
  }
  #thirdText {
    color: #f25260;
  }
`;
// 앱 설명 부분
export const AppContentWrapper = styled.div`
  flex: 1;
  border-radius: 1rem;
  margin: 2rem 2rem 0 3rem;
  padding: 2rem 0 0 0;
  @keyframes motion {
    0% {
      margin-top: 0px;
    }
    100% {
      margin-top: 5px;
    }
  }
  animation: motion 0.6s linear 0s infinite alternate;
  img {
    width: 5rem;
    margin-bottom: 2rem;
  }
  p {
    margin-top: 3rem;
    font-size: 1.2rem;
  }
  div {
    display: flex;
  }
  .left {
    img {
      margin-right: 2rem;
    }
  }
  .right {
    img {
      margin-left: 1.5rem;
    }
  }
`;

export const Discuss = styled.div`
  text-align: center;
  font-size: 1.5rem;
  margin-bottom: 2.5rem;
  div {
    margin-top: 1rem;
    display: flex;
    justify-content: space-between;
  }
  img {
    width: 3rem;
    height: 3rem;
  }
`;

export const PreferenceButtonWrapper = styled.div`
  background-color: #41d992;
  height: 5rem;
  border-radius: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 2rem 2rem 0 2rem;
  button {
    border: none;
    background-color: transparent;
    font-size: 2rem;
    font-weight: bold;
    color: white;
  }
`;
