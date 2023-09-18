import { styled } from "styled-components";

export const MapWrapper = styled.div`
  width: 100%;
  height: 90%;
  z-index: 0;
`;
export const SearchResultWrapper = styled.div`
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
  #places-list {
    list-style: none;
  }
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

export const SearchListWrapper = styled.div`
  #places-list {
    padding: 0 1rem;
  }
  .info {
    // background: red;
    margin: 1.5rem 0;
    width: 100%;
  }

  a {
    text-decoration-line: none;
    color: black;
  }
  // 인덱스 이름
  .name {
    margin: 1rem 0;
    display: flex;

    h5 {
      width: 100%;
      font-size: 1.3rem;
      margin: 0;
    }
  }
  #homePage {
    width: 5rem;
    height: 1rem;
    text-decoration: none;
    color: #f9373f;
    border: 1px solid #f9373f;
    font-size: 0.9rem;
    padding: 0.25rem;
    margin: 0 1rem;
    border-radius: 0.5rem;
  }

  // 주소
  .address {
  }
  // 번호
  .tel {
    display: block;
    margin: 0.5rem 0;
  }
  .diretion button {
    width: 3rem;
    height: 1.5rem;
    background: none;
    text-decoration: none;
    color: #f9373f;
    border: 1px solid #f9373f;
    font-size: 0.9rem;
    padding: 0.25rem;
    border-radius: 0.5rem;
  }
`;
