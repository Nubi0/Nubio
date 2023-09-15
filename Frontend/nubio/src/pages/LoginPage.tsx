import { useNavigate } from "react-router-dom";
import { LoginPageWrapper, LoginForm } from "../styles/SLoginPage";
import { LoginLogo } from "../styles/SSignUpPage";

const LoginPage = () => {
  const logoUrl = process.env.PUBLIC_URL + "/assets/nubio.png";
  const navigate = useNavigate();
  return (
    <LoginPageWrapper>
      <LoginLogo src={logoUrl} onClick={() => navigate("/")} />
      <LoginForm>
        <input type="text" name="id" placeholder="아이디(이메일 형식)" />
        <input type="password" name="pw" placeholder="비밀번호" />
        <button type="submit" id="login">
          로그인
        </button>
        <button id="text" onClick={() => navigate("/signUp")}>
          회원이 아니신가요?
        </button>
      </LoginForm>
    </LoginPageWrapper>
  );
};

export default LoginPage;
