import { styled } from "styled-components";

export const SignUpWrapper = styled.div`
  // width: 100%;
  // height: 100%;
  text-align: center;
`;
export const Logo = styled.img`
  width: 20rem;
  height: 20rem;
`;
export const SForm = styled.form`
  background-color: red;
  margin: auto;
  span {
    display: block;
    margin-bottom: 1rem;
  }
  .checkbox {
    label {
      margin: 5px 10px;
    }

    #male,
    #female {
      display: flex;
      align-items: center;
      width: 2rem;
      height: 2rem;
      padding: 0 1rem 0 1rem;
    }
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
    font-weight: 500;
    type: text;
    width: 15rem;
    font-size: 1.2rem;
    height: 2rem;
    padding: 0 1rem 0 1rem;
    border-radius: 0.5rem;
  }
`;
