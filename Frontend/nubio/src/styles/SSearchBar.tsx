import styled from "styled-components";

export const SearchBarWrapper = styled.div`
  position: absolute;
  top: 60px;
  left: 30px;
  input {
    width: 20rem;
    height: 2rem;
    border: 0.1rem solid black;
    border-radius: 1rem;
    padding: 0 0 0 2rem;
  }
  input::placeholder {
    font-size: 0.8rem;
  }

  img {
    position: absolute;
    top: 9px;
    left: 10px;
    width: 18px;
    height: 18px;
  }
`;
