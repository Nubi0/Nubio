import { LoginPageWrapper, LoginForm } from "../styles/SLoginPage";

const LoginPage = () => {
    return(
        <LoginPageWrapper>
            <img src="https://github.com/Nubi0/Nubio/assets/110621233/dd0c6a66-ca3c-4e4a-9406-89248326a3e5" alt="" />
            <LoginForm>
                <input type="text" name="id" placeholder="아이디(이메일 형식)" />
                <input type="password" name="pw" placeholder="비밀번호" />
                <button type="submit" id="login">로그인</button>
                <button id="text">회원이 아니신가요?</button>
            </LoginForm>
        </LoginPageWrapper>
    );
};

export default LoginPage;