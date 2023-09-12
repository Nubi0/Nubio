import styled from "styled-components";

export const SearchBarWrapper = styled.div`
  position: absolute;
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

// 검색결과
export const SearchResultWrapper = styled.div`
  width: 100%;
  height: 400px;
  background: white;
  margin-top: 1rem;
  border: 0.1rem solid black;
  border-radius: 1rem;
  overflow: scroll;
`;
export const BackButton = styled.button``;

export const SearchItemWrapper = styled.div`
  padding: 0.5rem;
  margin-top: 1rem;
  display: flex;
  justify-content: space-between;
`;

export const PlaceInfoWrapper = styled.div``;
export const PlaceName = styled.div`
  font-size: 1.5rem;
`;

export const PlaceAddress = styled.div`
  font-size: 0.5rem;
  margin: 0.25rem;
`;
export const PlaceNumber = styled.div`
  margin: 0.25rem 0;
  font-size: 0.8rem;
`;
export const PlaceHome = styled.a`
  text-decoration: none;
  color: #f9373f;
  border: 1px solid #f9373f;
  font-size: 0.5rem;
  padding: 0.25rem;
  border-radius: 0.5rem;
`;
export const DetailButton = styled.button`
  position: relative;
  top: -3.15rem;
  left: 4rem;
  text-decoration: none;
  color: #f9373f;
  border: 1px solid #f9373f;
  font-size: 0.5rem;
  padding: 0.25rem;
  margin-top: 1rem;
  border-radius: 0.5rem;
  background: transparent;
`;
export const PlacePhotoWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  img {
    width: 5rem;
    height: 3.5rem;
    margin: 0 0.5rem;
  }
`;
export const PlaceReviewWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-top: 0.5rem;
`;
export const Score = styled.div``;

export const ReviewCount = styled.div``;

// 검색 결과 상세보기
export const DetailItemWrapper = styled.div`
  width: 100%;
  height: 50%;
  background: white;
  position: absolute;
  bottom: 1rem;
`;
