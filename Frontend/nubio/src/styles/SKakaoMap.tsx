import { styled } from "styled-components";

export const MapWrapper = styled.div`
  width: 100%;
  height: 100%;
  z-index: 0;
`;
export const SearchResultsWrapper = styled.div`
  position: absolute;
  z-index: 3;
  top: 11%;
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
export const ClearRouteButton = styled.button`
  float: right;
  border: 0.1rem solid black;
  border-radius: 0.5rem;
  font-size: 1rem;
  padding: 0.2rem 0.5rem;
  background: transparent;
  margin-right: 0.5rem;
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

export const DestinationWrapper = styled.div`
  h4 {
    width: 90%;
    border: 0.1rem solid black;
    border-radius: 0.5rem;
    padding: 0.3rem 0.5rem;
    margin: 0.5rem;
  }
`;
// 내 위치
export const MyLocationButton = styled.button`
  float: right;
  border: 0.1rem solid black;
  border-radius: 0.5rem;
  font-size: 1rem;
  padding: 0.2rem 0.5rem;
  background: transparent;
`;
// 최소경로
export const ShortDirectionButton = styled.button`
  float: right;
  border: 0.1rem solid black;
  border-radius: 0.5rem;
  font-size: 1rem;
  padding: 0.2rem 0.5rem;
  background: transparent;
  margin-right: 0.5rem;
`;

// 안전경로
export const SafeDirectionButton = styled.button`
  float: right;
  border: 0.1rem solid black;
  border-radius: 0.5rem;
  font-size: 1rem;
  padding: 0.2rem 0.5rem;
  background: transparent;
  margin-right: 0.5rem;
`;
