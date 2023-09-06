import { styled } from "styled-components";

export const SignUpWrapper = styled.div`
  text-align: center;
`;
export const Logo = styled.img`
  width: 20rem;
  height: 8rem;
  margin: 3rem 0 1rem 0;
`;
export const Form = styled.form`
  padding: 3rem;
  span {
    display: flex;
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
  }
  #check {
    margin-left: 1rem;
    width: 3rem;
    border: none;
    border-radius: 1rem;
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
  text-align:center;
  justify-content:space-around;
  margin:2rem 2rem 1rem 2rem;
  #man,
  #woman {
    display: none;
  }
  #manCheck {
    background-color: #2E9AFE;
  }
  #womanCheck {
    background-color: pink;
  }
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
  border-radius: 1rem;
  // background-color:yellow;
  font-size: 1.5rem;
  font-weight: 600;
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
export const PrefrenceModalWrapper = styled.div``;

export const PrefrenceModalOverlay = styled.div`
  z-index: 9999;
  width: 100vw;
  height: 100vh;
  position: absolute;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const PrefrenceModalBox = styled.div`
  display: block;
  background: white;
  width: 20%;
  height: 50%;
  padding: 1rem;
  border-radius: 1rem;
  margin-bottom: 10%;
  text-align: center;
`;
