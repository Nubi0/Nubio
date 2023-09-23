import styled from 'styled-components';

export const MyCoursePageWrapper = styled.div`
    display: flex;
    flex-direction: column;
`;

export const MyCourseWrapper = styled.div`
    margin: 0 1rem;
    padding-bottom: 5.4rem;
`;

export const MyCourseHeader = styled.div`
    font-size: 2rem;
    font-weight: bold;
`;

export const LocationListWrapper = styled.div`
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    place-items: center;
    margin-top: 2rem;
`;

export const LocationItemWrapper = styled.div`
    position: relative;
    margin-top: 1rem;
`;

export const LocationImg = styled.img`
    width: 9.6rem;
    height: 4.6rem;
    border-radius: 1rem;
`;

export const LocationText = styled.div`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%); 
    color: white; 
`;

export const TextWrapper = styled.div`
    background-color: rgba(0, 0, 0, 0.5);
    width: 9.6rem;
    height: 4.6rem;
    position: absolute; 
    top: 50%; 
    left: 50%; 
    transform: translate(-50%, -50%); 
    border-radius: 1rem;
`;
