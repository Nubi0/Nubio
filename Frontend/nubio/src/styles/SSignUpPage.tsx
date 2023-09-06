import { styled } from "styled-components";

export const SignUpWrapper = styled.div`
  text-align: center;
`;
export const Logo = styled.img`
  width: 20rem;
  height: 8rem;
  margin: 3rem 0 1rem 0;
`;
export const SForm = styled.form`
  padding: 3rem;
  span {
    display: block;
    margin-bottom: 1rem;
  }
  input::placeholder {
    font-size: 1rem;
    font-weight: 600;
  }

  #text {
    font-family: "Pretendard";
    font-weight: 500;
    type: text;
    width: 15rem;
    height: 3rem;
    padding: 0 1rem 0 1rem;
    border-radius: 0.5rem;
  }
  #date {
    font-family: "Pretendard";
    font-weight: 600;
    width: 15rem;
    height: 3rem;
    font-size: 1.2rem;
    color: gray;
    padding: 0 1rem 0 1rem;
    border-radius: 0.5rem;
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
