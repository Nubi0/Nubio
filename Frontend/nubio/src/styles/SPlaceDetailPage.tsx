import styled from 'styled-components';

export const PlaceDetailPageWrapper = styled.div`
`;

export const PlaceDetailWrapper = styled.div`
    img{
        width: 100%;
        min-height: 26rem;
    }
`;

export const PlaceItemWrapper = styled.div``;

export const PlaceLikeButtonWrapper = styled.div`
position: fixed;
bottom: 5.4rem;
width: 100%;
    button{
        display: flex;
        align-items: center;
        justify-content: center;
        padding: 1rem;
        border: none;
        color: white;
        background-color: #ffc542;
        width: 100%;
        font-size: 1.5rem;
    }
    span{
        margin-left: 1rem;
    }
`;

export const PlaceDetailInfoWrapper = styled.div`
    border-radius: 0.8rem 0.8rem 0 0;
    width: 100%;
    position: absolute;
    top: 45%;
    height: 100%;
    background-color: white;
`;

export const PlaceMapWrapper = styled.div`
    width: 100%;
`;

export const PlaceReviewWrapper = styled.div`
    margin: 0 1rem;
    margin-top: 1rem;
`;

export const ReviewHeader = styled.div`
    display: flex;
    max-height: 2rem;
    img{
        width: 36px;
        height: 36px;
    }
`;

export const ReviewForm = styled.form`
    display:flex;
    justify-content: space-between;
    margin-top: 1rem;
    input{
        border: none;
        width: 100%;
    }
`;

export const MapWrapper = styled.div`
    width: 100%;
    height: 7.68rem;

`;

export const MapHeader = styled.div`
    margin: 1rem 1rem 0.5rem 1rem;
`;