import styled from 'styled-components';

export const EnjoyHomePageWrapper = styled.div`
    height: 100%;
    position: relative;
`;

export const AllButtonWrapper = styled.div`
    display: flex;
    justify-content: space-between;
    margin: 0 1.5rem;
    & > button{
        border: none;
        color: #FFC542;
        background-color: transparent;
    }
`;

export const ContentWrapper = styled.div`
    
`;

export const FitText = styled.span`
    color: #F9373F;
    font-size: 2rem;
    font-family: 'NanumSquareExtraBold';
    text-decoration: underline;
`;

export const Content = styled.span`
    color: black;
    font-size: 24px;
    font-weight: bold;
    font-family: 'NanumSquareExtraBold';
`;

export const AllButtonImg = styled.img``;

export const BannerWrapper = styled.div``;

export const BannerImg = styled.img`
    width: 100%;
`;

export const CustomCouresListWrapper = styled.div`
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    margin: 1rem;
    place-items: center;
`;

export const CustomCourseItemWrapper = styled.div`
    margin-bottom: 1.5rem;
`;

export const RepresentativeImg = styled.img`
    width: 7rem;
    height: 6.5rem;
    border-radius: 1.25rem;
`;

export const BookMarkImg = styled.img`
    width: auto;
    height: auto;
`;

export const TitleWrapper = styled.div`
    display: flex;
    justify-content: space-between;
`;

export const CourseMakerIconWrapper = styled.div`
    position: absolute;
    bottom: 3.4rem;
    right: 1rem;
`;