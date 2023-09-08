import styled from 'styled-components';

export const CourseDetailPageWrapper = styled.div`
    display: flex;
    flex-direction: column;
`;

export const CourseDetailWrapper = styled.div`
    margin: 0 1rem;
    padding-bottom: 5.4rem;
    position: relative;
    background-color: red;

`;

export const CourseDetailListWrapper = styled.div`
    position: absolute;
    bottom: 5.4rem;
    width: 100%;
    border-radius: 30px;
    background-color: red;
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
    border-radius: 1rem 1rem 0 0;
    margin: 0 1rem;
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
    background-color: #FFC542;
    display: flex;
    justify-content: space-between;
    padding: 0.5rem 2rem;
    div{
        display: flex;
        background-color: transparent;
        align-items: center;
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
    width: 0.25rem;
    background-color: white;
    margin: 0 1rem;
`;

export const DetailButton = styled.div`
    width: 100%;
    margin-top: 0.7rem;
    display: flex;
    justify-content: center;
    button{
        width: 80%;
        border: 1px solid #f9373f;
        background-color: transparent;
        border-radius: 2rem;
        color: #f9373f;
    }
`;