import SignUpForm from "../components/user/signUp/SignUpForm";
import {
  SignUpWrapper,
  SignUpLogo,
  CheckUserWrapper,
} from "../styles/SSignUpPage";
import { useNavigate } from "react-router-dom";

const SignUpPage = () => {
  const navigate = useNavigate();

  // 이미지
  const logoUrl = process.env.PUBLIC_URL + "/assets/nubio.png";
  return (
    <SignUpWrapper>
      <SignUpLogo src={logoUrl} onClick={() => navigate("/")} />
      <SignUpForm />
      <CheckUserWrapper>
        <a onClick={() => navigate("/login")}>이미 회원이신가요?</a>
      </CheckUserWrapper>
    </SignUpWrapper>
  );
};

export default SignUpPage;
