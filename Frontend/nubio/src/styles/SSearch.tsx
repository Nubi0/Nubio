import styled from "styled-components";

export const SearchWrapper = styled.div``;
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

// 검색 결과
export const SearchResultsWrapper = styled.div`
  position: absolute;
  top: 7rem;
  left: 1rem;
  width: 85%;
  height: 15rem;
  padding: 0.5rem;
  background: white;
  margin-top: 1rem;
  border: 0.1rem solid black;
  border-radius: 1rem;
  overflow-y: scroll;
  z-index: 1;
  .result-text {
    font-size: 1.3rem;
    margin: 0;
  }
  #pagination {
    text-align: center;
    a {
      text-decoration: none;
      color: black;
      margin: 10px;
    }
  }
`;
// 출발 도착
export const SetDirectionWrapper = styled.div`
  width: 100%;
  height: 6rem;
  // text-align: center;
  input {
    width: 85%;
    height: 2rem;
    margin: 0.1rem;
    padding: 0 1rem;
    border-radius: 0.5rem;
    border: 0.1rem solid black;
  }
  button {
    float: right;
    width: 5rem;
    height: 2rem;
    margin: 0.5rem 1rem;
    border-radius: 0.5rem;
    border: 0.1rem solid black;
  }
`;
export const SearchListWrapper = styled.div`
  #places-list {
    padding: 0 1rem;
  }
`;
