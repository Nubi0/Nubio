import styled from 'styled-components';

// 첫 홈페이지
export const HomePageWrapper = styled.div`
    display: flex;
    flex-direction: column;
`;

export const Logo = styled.img`
    margin: 4rem 2rem 0 2rem;
`;

export const ButtonWrapper = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    background-color: #41D992;
    padding: 1rem;
    border-radius: 1rem;
    margin: 0 2rem;
`


// 첫 홈페이지 안전 버튼
export const SafeButtonWrapper = styled.div`
    background-color: #F25260;
    width: 10rem;
    height: 12rem;
    border-radius: 1rem;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    button{
        border: none;
        background-color: transparent;
        font-size: 2rem;
        font-weight: bold;
        color: white;
    }
        img{
        width: 7rem;
        height: 7rem;
        padding: 1rem;
    }
`;

// 첫 페이지 흥미 버튼
export const EnjoyButtonWrapper = styled.div`
    background-color: #FFC542;
    width: 10rem;
    height: 12rem;
    border-radius: 1rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    button{
        border: none;
        background-color: transparent;
        font-size: 2rem;
        font-weight: bold;
        color: white;
    }
    img{
        width: 7rem;
        height: 7rem;
        padding: 1rem;
    }
`;

// 첫 홈페이지 시작하기 버튼
export const StartButtonWrapper = styled.div`
    background-color: #41D992;
    height: 5rem;
    border-radius: 1rem;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 2rem 2rem 0 2rem;
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
    flex: 1;
    border-radius: 1rem;
    margin: 2rem 2rem 0 2rem;
`;

export const Discuss = styled.div`
    text-align: center;
    font-size: 1.5rem;
    margin-bottom: 2.5rem;
    div{
        margin-top: 1rem;
        display: flex;
        justify-content: space-between;
    }
    img{
        width: 3rem;
        height: 3rem;
    }
`;

export const PreferenceButtonWrapper = styled.div`
background-color: #41D992;
    height: 5rem;
    border-radius: 1rem;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 2rem 2rem 0 2rem;
    button{
        border: none;
        background-color: transparent;
        font-size: 2rem;
        font-weight: bold;
        color: white;
    }
`;
