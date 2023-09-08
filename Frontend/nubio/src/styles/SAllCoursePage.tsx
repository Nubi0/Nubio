import styled from 'styled-components';

export const AllCoursePageWrapper = styled.div`
  margin: 0 1rem;
  display: flex;
  flex-direction: column;
`;

export const AllCourseWrapper = styled.div`
  padding-bottom: 5.4rem;
`;

export const AllCourseHeaderWrapper = styled.div`
  display: flex;
  justify-content: space-between;
`;

export const FirstLine = styled.div`
  display: flex;
`;

export const SecondLine = styled.div`
  font-family: 'NanumSquareExtraBold';
  font-weight: bold;
  font-size: 1.5rem;
  span:first-child {
    color: #ffc542;
  }
`;

export const LeftSide = styled.div``;

export const RightSide = styled.div``;

export const CategoryWrapper = styled.div`
  display: flex;
  .active {
    color: #ffc542;
  }
`;

export const AllCourseListWrapper = styled.div`
  margin-top: 1rem;
`;

export const AllCourseItemWrapper = styled.div`
  display: flex;
  overflow-x: scroll;
  margin-top: 1.5rem;
`;

export const BigImg = styled.img`
  width: 6.75rem;
  height: 6.5rem;
  border-radius: 0.5rem;
`;

export const DetailWrapper = styled.div`
  margin-left: 1rem;
`;

export const HeadContent = styled.div`
  display: flex;
  align-items: center;
`;

export const Title = styled.div`
  font-family: 'NanumSquareExtraBold';
  font-weight: bold;
`;

export const CourseTag = styled.div`
  border: 0.5px solid #f9373f;
  border-radius: 10px;
  font-size: 0.5rem;
  width: 2rem;
  height: 1rem;
  color: #f9373f;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 0.25rem;
`;

export const FavImg = styled.img`
  margin-left: 0.25rem;
`;

export const PlaceItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-right: 0.5rem;
`;

export const PlaceImg = styled.img`
  width: 3.5rem;
  height: 3.25rem;
  border-radius: 0.5rem;
`;

export const PlaceName = styled.div`
  font-size: 0.5rem;
`;

export const PlaceListWrapper = styled.div`
  display: flex;
  margin-top: 0.5rem;
`;

export const EnjoyHeaderWrapper = styled.div`
  margin: 1rem 0;
  display: flex;
  align-items: center;
  font-size: 1.5rem;
  div {
    margin-left: 1rem;
  }
`;

export const ModalWrapper = styled.div`
  position: absolute;
  background-color: rgba(0, 0, 0, 0.5);
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Modal = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  width: auto;
  height: auto;
  padding: 1rem;
  position: relative;
  border-radius: 1rem;
  margin: 0 1rem;
`;

export const ModalHeader = styled.div``;

export const ModalBody = styled.div`
  display: flex;
  flex-wrap: wrap;
  margin-top: 1rem;
`;

export const ModalFooter = styled.div`
  text-align: end;
  margin-top: 1rem;
`;

export const PurposeItemWrapper = styled.div<{ isSelected: boolean }>`
  border: 0.5px solid ${(props) => (props.isSelected ? '#f9373f' : 'gray')};
  border-radius: 1rem;
  font-size: 0.5rem;
  color: ${(props) => (props.isSelected ? '#f9373f' : 'gray')};
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.25rem;
  margin-right: 0.25rem;
  margin-top: 0.5rem;
`;
