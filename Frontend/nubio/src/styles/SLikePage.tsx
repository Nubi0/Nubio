import styled from "styled-components";

export const LikePageWrapper = styled.div`
  height: 100%;
  position: relative;
`;

export const PlaceListWrapper = styled.div``;

export const LikeHeaderWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  .active {
    border-bottom: 2px solid #ffc542;
  }
`;

export const Place = styled.div`
  font-size: 1.5rem;
  width: 100%;
  text-align: center;
`;

export const Course = styled.div`
  font-size: 1.5rem;
  width: 100%;
  text-align: center;
`;

export const LikeListWrapper = styled.div`
  margin: 0 1rem;
  height: calc(100% - 5rem);
`;

export const PlaceItemWrapper = styled.div`
  background-color: rgba(0, 0, 0, 0.04);
  padding: 1rem;
  border-radius: 1rem;
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
  margin-top: 0.25rem;
`;

export const PlaceHome = styled.a`
  text-decoration: none;
  color: #f9373f;
  border: 1px solid #f9373f;
  font-size: 0.5rem;
  padding: 0.25rem;
  margin-top: 1rem;
  border-radius: 0.5rem;
`;

export const PlaceNumber = styled.div`
  margin-top: 0.25rem;
  font-size: 0.8rem;
`;

export const PlacePhotoWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  img {
    width: 4.75rem;
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

export const CourseListWrapper = styled.div`
  overflow-y: scroll;
  height: calc(100% - 3.4rem);
`;
