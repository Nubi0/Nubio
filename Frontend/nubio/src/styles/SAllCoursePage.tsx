import styled from 'styled-components';

export const AllCoursePageWrapper = styled.div`
    margin: 0 1rem;
`;

export const AllCourseWrapper = styled.div``;

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
    span:first-child{
        color: #FFC542;
    }
`;


export const LeftSide = styled.div``;

export const RightSide = styled.div``;

export const CategoryWrapper = styled.div`
    display: flex;
    .active{
        color: #FFC542;
    }
`;

export const AllCourseListWrapper = styled.div`
    margin-top: 1rem;
`;

export const AllCourseItemWrapper = styled.div`
    display: flex;
    overflow-x: hidden;
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
    border: 0.5px solid #F9373F;
    border-radius: 10px;
    font-size: 0.5rem;
    width: 2rem;
    height: 1rem;
    color: #F9373F;
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
    div{
        margin-left: 1rem;
    }
`;