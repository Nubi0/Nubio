import { styled } from "styled-components";

export const SignUpWrapper = styled.div`
  text-align: center;
  width: 430px;
  height: 932px;
`;
export const SignUpLogo = styled.img`
  width: 20rem;
  height: 8rem;
  margin: 3rem 0 1rem 0;
`;
export const LoginLogo = styled.img`
  width: 20rem;
  margin: 1rem 0 1rem 0;
`;
export const Form = styled.form`
  span {
    margin-bottom: 1rem;
    width: 20rem;
  }
  input {
    font-family: "Pretendard";
    font-weight: 500;
    type: text;
    width: 18rem;
    height: 3rem;
    padding: 0 1rem 0 1rem;
    border-radius: 0.5rem;
    margin: 0.5rem 0;
  }
  #checkEmail {
    position: absolute;
    left: 16.5rem;
    border: none;
    border-radius: 1rem;
    font-weight: 600;
    margin: 1.5rem;
    background-color: #ffc542;
  }
  #checkNickname {
    position: absolute;
    left: 17.5rem;
    border: none;
    border-radius: 1rem;
    font-weight: 600;
    margin: 1.5rem;
    background-color: #ffc542;
  }
  #check:disabled {
    background-color: transparent !important;
    color: #14ca48;
    font-weight: 600;
  }
  #date {
    font-family: "Pretendard";
    font-weight: 600;
    width: 18rem;
    height: 3rem;
    font-size: 1.2rem;
    color: gray;
    padding: 0 1rem 0 1rem;
    border-radius: 0.5rem;
  }
  input::placeholder {
    font-size: 1rem;
    font-weight: 600;
  }
`;

export const GenderWrapper = styled.div`
  display: flex;
  text-align: center;
  justify-content: space-around;
  margin: 2rem 2rem 1rem 2rem;
  #man,
  #woman {
    display: none;
  }
  #manCheck {
    border-radius: 1rem;
    background-color: #2e9afe;
  }
  #womanCheck {
    border-radius: 1rem;
    background-color: pink;
  }
`;
export const ManIcon = styled.img`
  width: 7rem;
  height: 5rem;
`;
export const WomanIcon = styled.img`
  width: 7rem;
  height: 5rem;
`;

export const SubmitButton = styled.button`
  width: 10rem;
  height: 3rem;
  margin: 2rem;
  border: none;
  border-radius: 1rem;
  font-size: 1.5rem;
  font-weight: 600;
  background-color: #ffc542;
`;

export const CheckUserWrapper = styled.div`
  a {
    font-family: "Pretendard";
    font-weight: 600;
    font-size: 1.2rem;
    color: gray;
  }
`;

// 모달
export const ModalWrapper = styled.div`
  img {
    width: 3rem;
  }
  h2 {
    margin: 0;
  }
`;

export const PrefrenceModalOverlay = styled.div`
  z-index: 9999;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  background: rgba(0, 0, 0, 0.7);
`;

export const PrefrenceModalBox = styled.div`
  background: white;
  width: 80%;
  padding: 1rem;
  border-radius: 1rem;
  text-align: center;
  img {
    width: 3rem;
    height: 3rem;
    border: solid black 2px;
    border-radius: 2rem;
    padding: 0.2rem;
  }
  #check {
    background-color: #ffc542;
  }
  button {
    width: 4rem;
    height: 2rem;
    font-size: 1rem;
    font-weight: 600;
    border: none;
    border-radius: 1rem;
    background-color: #ffc542;
    margin: 1rem;
  }
`;

export const EatWrapper = styled.div`
  width: 100%;
`;
export const DrinkWrapper = styled.div`
  width: 100%;
`;
export const PlayWrapper = styled.div`
  width: 100%;
`;

export const IconWrapper = styled.div`
  width: 100%;
  img {
    margin: 0.2rem;
  }
`;
