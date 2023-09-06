import SetPrefrenceModal from "../components/user/SetPrefrenceModal";
import SignUpForm from "../components/user/signUp/SignUpForm";
import { SignUpWrapper, Logo, CheckUserWrapper } from "../styles/SSignUpPage";

const SignUpPage = () => {
  const logoUrl = process.env.REACT_APP_LOGO_URL;
  return (
    <SignUpWrapper>
      <Logo src={logoUrl} />
      <SetPrefrenceModal />
      <SignUpForm />
      <CheckUserWrapper>
        <a href="">이미 회원이신가요?</a>
      </CheckUserWrapper>
    </SignUpWrapper>
  );
};

export default SignUpPage;
