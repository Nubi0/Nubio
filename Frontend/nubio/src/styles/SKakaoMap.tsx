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
    padding: 0;
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
    margin: 0;
    h5 {
      font-size: 1.3rem;
      margin: 1rem 0;
    }
  }

  // 주소
  .address {
  }
  // 번호
  .tel {
  }
`;
