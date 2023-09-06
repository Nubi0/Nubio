import SignUpForm from "../components/user/signUp/SignUpForm";
import { SignUpWrapper, Logo } from "../styles/SSignUpPage";

const SignUpPage = () => {
  const logoUrl = process.env.REACT_APP_LOGO_URL;
  return (
    <SignUpWrapper>
      <Logo src={logoUrl} />
      <SignUpForm />
    </SignUpWrapper>
  );
};

export default SignUpPage;
