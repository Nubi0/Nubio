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
`;

// SearchItem

export const SearchItemWrapper = styled.div`
  margin: 1.5rem 0;
  width: 100%;
`;

export const ItemNameWrapper = styled.div`
  margin: 1rem 0;
  display: flex;

  h5 {
    width: 100%;
    font-size: 1.3rem;
    margin: 0;
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
`;

export const ItemInfoWrapper = styled.div`
  // 번호
  .tel {
    display: block;
    margin: 0.5rem 0;
  }
`;
export const DirectionButtonWrapper = styled.div`
  button {
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

// SetDirection

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
`;
