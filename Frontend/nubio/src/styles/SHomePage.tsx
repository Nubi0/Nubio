import styled from 'styled-components';

// 첫 홈페이지
export const HomePageWrapper = styled.div`
    display: flex;
    flex-direction: column;
    margin: 4rem 2rem 0 2rem;
`;
export const ButtonWrapper = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
`


// 첫 홈페이지 안전 버튼
export const SafeButtonWrapper = styled.div`
    background-color: #F25260;
    width: 10rem;
    height: 14.5rem;
    border-radius: 1rem;
    display: flex;
    justify-content: center;
    align-items: center;
    button{
        border: none;
        background-color: transparent;
        font-size: 2rem;
        font-weight: bold;
        color: white;
    }
`;

// 첫 페이지 흥미 버튼
export const EnjoyButtonWrapper = styled.div`
    background-color: #FFC542;
    width: 10rem;
    height: 14.5rem;
    border-radius: 1rem;
    display: flex;
    justify-content: center;
    align-items: center;
    button{
        border: none;
        background-color: transparent;
        font-size: 2rem;
        font-weight: bold;
        color: white;
    }
`;

// 첫 홈페이지 시작하기 버튼
export const StartButtonWrapper = styled.div`
    background-color: #41D992;
    width: 100%;
    height: 9.5rem;
    margin-top: 2rem;
    border-radius: 1rem;
    display: flex;
    justify-content: center;
    align-items: center;
    button{
        border: none;
        background-color: transparent;
        font-size: 2rem;
        font-weight: bold;
        color: white;
    }
`;

// 앱 설명 부분
export const AppContentWrapper = styled.div`
    background-color: #41D992;
    flex: 1;
    border-radius: 1rem;
    margin-top: 2rem;
`;