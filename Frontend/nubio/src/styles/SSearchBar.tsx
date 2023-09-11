import styled from "styled-components";

export const SearchBarWrapper = styled.div`
  position: absolute;
  top: 60px;
  left: 30px;
  z-index: 1;
  input {
    width: 20rem;
    height: 2.5rem;
    border: 0.1rem solid black;
    border-radius: 1rem;
    padding: 0 0 0 2rem;
  }
  input::placeholder {
    font-size: 1rem;
  }

  img {
    position: absolute;
    top: 0.8rem;
    left: 10px;
    width: 1rem;
    height: 1rem;
  }
`;
