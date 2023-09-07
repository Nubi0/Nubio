import { LoginPageWrapper, LoginForm } from "../styles/SLoginPage";


const LoginPage = () => {
    const logoUrl = process.env.REACT_APP_LOGO_URL;

    return(
        <LoginPageWrapper>
            <img src={logoUrl} alt="" />
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