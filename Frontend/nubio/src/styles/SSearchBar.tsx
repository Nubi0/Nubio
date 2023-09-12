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

// 검색결과
export const SearchResultWrapper = styled.div`
  max-width: 100%;
  height: 400px;
  padding: 0.5rem;
  background: white;
  margin-top: 1rem;
  border: 0.1rem solid black;
  border-radius: 1rem;
  overflow-y: scroll;
`;

export const SearchItemWrapper = styled.div`
  padding: 0.5rem;
  display: flex;
  justify-content: space-between;
`;

export const PlaceInfoWrapper = styled.div``;
export const PlaceName = styled.div`
  font-size: 1.5rem;
`;

export const PlaceAddress = styled.div`
  font-size: 0.7rem;
  margin: 0.25rem;
`;
export const PlaceNumber = styled.div`
  margin: 0.25rem 0;
  font-size: 0.9rem;
`;
export const PlaceHome = styled.a`
  text-decoration: none;
  color: #f9373f;
  border: 1px solid #f9373f;
  font-size: 0.5rem;
  padding: 0.25rem;
  border-radius: 0.5rem;
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
  max-width: 100%;
  background: white;
  padding: 1rem;
`;
export const DetailPlaceInfoWrapper = styled.div``;
export const DetailPlaceName = styled.div`
  font-size: 2rem;
`;

export const DetailPlaceAddress = styled.div`
  font-size: 1rem;
  margin: 0.25rem 0;
`;
export const DetailPlaceNumber = styled.div`
  margin: 0.25rem 0 0.8rem 0;
  font-size: 1rem;
`;
export const DetailPlaceHome = styled.a`
  text-decoration: none;
  color: #f9373f;
  border: 1px solid #f9373f;
  font-size: 1rem;
  padding: 0.25rem;
  border-radius: 0.5rem;
`;

export const DetailPlacePhotoWrapper = styled.div`
  width: 90%;
  margin: 1rem;
  display: flex;
  overflow-x: scroll;
`;

export const DetailButton = styled.button`
  position: relative;
  top: -2.6rem;
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

export const DetailPlaceReviewWrapper = styled.div`
  display: flex;
  justify-content: space-between;
`;
export const DetailReviewCount = styled.div`
  font-size: 1.5rem;
`;

export const DetailScore = styled.div`
  font-size: 1.5rem;
`;
export const DetailReviewForm = styled.form`
  input {
    margin-top: 1rem;
    padding: 0;
    border: none;
    width: 100%;
  }
  input::placeholder {
    padding: 1rem;
  }
`;
export const BackButton = styled.button`
  width: 1.5rem;
  padding: 0.1em;
  background: #ffc542;
  border: none;
  border-radius: 1rem;
  position: absolute;
  top: 4.1rem;
  right: 0.4rem;
`;
