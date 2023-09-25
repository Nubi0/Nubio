import styled from 'styled-components';

// 로그인 페이지
export const LoginPageWrapper = styled.div`
    margin: 4rem 2rem 2rem 2rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    color: rgba(0, 0, 0, 0.46);
    img{
        margin-top: 20%;
    }
`;

export const LoginForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin-top: 3rem;
    input{
        width: 19rem;
        height: 2rem;
        border: none;
        border-radius: 0.5rem;
        margin-bottom: 0.5rem;
        background-color: #D9D9D9;
        padding: 0.5rem;
    }
    #login{
        width: 20rem;
        height: 3rem;
        border: none;
        margin-top: 1rem;
        background-color: #D9D9D9;
        border-radius: 0.5rem;
        color: rgba(0, 0, 0, 0.46);

    }
    #text{
        margin-top: 0.5rem;
        border: none;
        background-color: transparent;
        color: #D9D9D9;
    }
`;


export const KakaoLogin = styled.img`
    margin-top: 3rem!important;
`;
