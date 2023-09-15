import styled from "styled-components";

export const SearchBarWrapper = styled.div`
  position: absolute;
  width: 83%;
  top: 60px;
  left: 30px;
  z-index: 2;
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
  .readingGlasses {
    position: absolute;
    top: 0.8rem;
    left: 10px;
    width: 1rem;
    height: 1rem;
  }
`;

// 검색 폼
export const SearchForm = styled.form``;
