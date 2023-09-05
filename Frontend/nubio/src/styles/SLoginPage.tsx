import styled from 'styled-components';

// 로그인 페이지
export const LoginPageWrapper = styled.div`
    margin: 2rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    img{
        width: 20rem;
        height: 20rem;
    }
`;

export const LoginForm = styled.form`
    display: flex;
    flex-direction: column;
    margin-top: 2rem;
    input{
        width: 15.5rem;
        height: 2rem;
    }
    button{
    }
    #text{
        border: none;
        background-color: transparent;
        color: rgba(0, 0, 0, 0.46)
    }
`;
