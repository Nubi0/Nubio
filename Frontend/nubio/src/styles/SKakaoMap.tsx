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
  height: 200px;
  padding: 0.5rem;
  background: white;
  margin-top: 1rem;
  border: 0.1rem solid black;
  border-radius: 1rem;
  overflow-y: scroll;
  #places-list {
    list-style: none;
  }
`;
