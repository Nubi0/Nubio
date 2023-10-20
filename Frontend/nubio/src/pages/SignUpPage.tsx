import { useState } from "react";
import SignUpForm from "../components/user/signUp/SignUpForm";
import {
  SignUpWrapper,
  SignUpLogo,
  CheckUserWrapper,
} from "../styles/SSignUpPage";
import { useNavigate } from "react-router-dom";
import CircularProgress from "@mui/material/CircularProgress";
import Box from "@mui/material/Box";

const SignUpPage = () => {
  const navigate = useNavigate();
  const [isLoading, setIsLoading] = useState(false);

  // 이미지
  const logoUrl = process.env.PUBLIC_URL + "/assets/nubio.png";
  return (
    <SignUpWrapper>
      <SignUpLogo src={logoUrl} onClick={() => navigate("/")} />
      <SignUpForm setIsLoading={setIsLoading} />
      <CheckUserWrapper>
        <a onClick={() => navigate("/login")}>이미 회원이신가요?</a>
      </CheckUserWrapper>
      {isLoading && (
        <Box position="absolute" top="50%" right="50%">
          <CircularProgress />
        </Box>
      )}
    </SignUpWrapper>
  );
};

export default SignUpPage;
