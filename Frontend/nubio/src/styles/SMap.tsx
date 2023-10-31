import { styled } from "styled-components";

export const MapWrapper = styled.div`
  width: 100%;
  height: 100%;
  z-index: 0;
`;

// 검색창
export const SearchBarWrapper = styled.div`
  position: absolute;
  top: 3%;
  left: 6%;
  z-index: 2;
  width: 80%;
  input {
    width: 100%;
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

export const SearchResultsWrapper = styled.div`
  position: absolute;
  z-index: 3;
  top: 13%;
  left: 2%;
  width: 90%;
  height: 15rem;
  padding: 0.5rem;
  background: white;
  border: 0.1rem solid black;
  border-radius: 1rem;
  overflow-y: scroll;

  .result-text {
    font-size: 1.2rem;
    margin: 0.8rem;
    // border-bottom: 0.1rem solid black;
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
  margin-top: 3rem;
  #places-list {
    padding: 0 1rem;
    list-style: none;
  }
  .name {
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
  }
  .bnt {
    margin-top: 0.5rem;
    #start {
      width: 3rem;
      background: transparent;
      color: #ffc542;
      border: 0.1rem solid #ffc542;
      font-size: 0.9rem;
      padding: 0.25rem;
      margin: 0 0.1rem;
      border-radius: 0.5rem;
    }
    #end {
      width: 3rem;
      background: transparent;
      color: #f9373f;
      border: 0.1rem solid #f9373f;
      font-size: 0.9rem;
      padding: 0.25rem;
      margin: 0 0.1rem;
      border-radius: 0.5rem;
    }
  }
`;

// 내 위치를 출발지로
export const MyLocationButton = styled.button`
  float: right;
  border: 0.1rem solid black;
  border-radius: 0.5rem;
  font-size: rem;
  padding: 0.2rem 0.5rem;
  background: transparent;
`;
// 내 위치로 이동
export const MoveMyLocation = styled.img`
  position: absolute;
  top: 15%;
  right: -6%;
  padding: 0.3rem;
  background: transparent;
  z-index: 3;
  width: 1.3rem;
`;

// 목적지
export const DestinationWrapper = styled.div`
  h4 {
    width: 90%;
    border: 0.1rem solid black;
    border-radius: 0.5rem;
    padding: 0.3rem 0.5rem;
    margin: 0.5rem;
  }
`;

// 경로 다 지우는 버튼
export const ClearRouteButton = styled.button`
  float: right;
  border: 0.1rem solid black;
  border-radius: 0.5rem;
  font-size: 1rem;
  padding: 0.2rem 0.5rem;
  background: transparent;
  margin-right: 0.5rem;
`;
