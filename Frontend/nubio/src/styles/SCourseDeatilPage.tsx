import styled from 'styled-components';

export const CourseDetailPageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100vh;
`;

export const CourseDetailWrapper = styled.div`
  margin: 0 1rem;
  padding-bottom: 5.4rem;
  position: relative;
  background-color: white;
`;

export const CourseDetailListWrapper = styled.div`
  position: absolute;
  bottom: 7.4rem;
  width: 100%;
  border-radius: 30px 30px 0 0;
  background-color: rgba(255, 255, 255, 1);
  height: 37%;
  overflow-y: scroll;
`;

export const DetailHr = styled.hr`
  width: 50%;
  height: 3px;
  border-radius: 10px;
  background-color: gray;
`;

export const ItemWrapper = styled.div`
  display: flex;
  overflow-x: scroll;
  padding: 2rem 2.5rem 0 2.5rem;
`;

export const CourseDetailItemWrapper = styled.div`
  background-color: white;
  color: white;
  position: relative;
  width: 12.5rem;
  height: 14.25rem;
  border-radius: 1rem;
  margin: 0 1rem 1rem 1rem;
  border: 1px solid black;
`;

export const PlaceImg = styled.img`
  width: 12.5rem;
  height: 11rem;
  border-radius: 1rem 1rem 0 0;
`;

export const InfoWrapper = styled.div`
  display: flex;
  align-items: center;
  position: absolute;
  bottom: 23%;
  left: 0%;
  width: 100%;
  background: linear-gradient(to bottom, transparent, rgba(0, 0, 0, 1));
`;

export const Left = styled.div`
  font-size: 2.5rem;
`;

export const Right = styled.div`
  display: flex;
  flex-direction: column;
`;

export const CategoryName = styled.div`
  font-size: 0.5rem;
  color: lightgray;
  font-weight: light;
`;

export const PlaceName = styled.div`
  font-size: 1rem;
  font-weight: 800;
`;

export const Address = styled.div`
  font-size: 0.5rem;
  display: flex;
`;

export const CourseButtonWrapper = styled.div`
  background-color: #ffc542;
  display: flex;
  justify-content: space-between;
  z-index: 1;
  position: absolute;
  bottom: 3.46rem;
  width: 100%;
  div {
    display: flex;
    background-color: transparent;
    align-items: center;
    padding: 0.5rem 2rem;
  }
  button {
    background-color: transparent;
    border: none;
    color: white;
    margin-left: 0.5rem;
    font-size: inherit;
    padding: 0;
  }
`;

export const ButtonDivider = styled.hr`
  border: none;
  width: 0.1rem;
  background-color: white;
  margin: 0 1rem;
`;

export const DetailButton = styled.div`
  width: 100%;
  margin-top: 0.7rem;
  display: flex;
  justify-content: center;
  a {
    width: 80%;
    border: 1px solid #f9373f;
    background-color: transparent;
    border-radius: 2rem;
    color: #f9373f;
    text-decoration: none;
    text-align: center;
  }
`;

export const CourseReviewWrapper = styled.div`
  margin: 0 1rem;
  margin-top: 1rem;
  height: 20%;
  /* overflow-y: scroll; */
`;

export const ReviewHeader = styled.div`
  display: flex;
  max-height: 2rem;
  img {
    width: 36px;
    height: 36px;
  }
`;

export const ReviewForm = styled.form`
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
  margin-bottom: 1rem;
  input {
    border: none;
    width: 100%;
  }
  button {
    border: none;
    background-color: transparent;
    width: 30%;
  }
`;

export const CourseReviewListWrapper = styled.div``;

export const CourseReviewItemWrapper = styled.div`
  div {
    display: flex;
    justify-content: space-between;
  }
  button {
    border: none;
    background-color: transparent;
    color: gray;
  }
  margin-bottom: 2rem;
`;
