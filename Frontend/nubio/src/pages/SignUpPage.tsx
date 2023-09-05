import { SForm, SignUpWrapper, Logo } from "../styles/SSignUpPage";

const SignUpPage = () => {
  return (
    <SignUpWrapper>
      <Logo src="https://github.com/Nubi0/Nubio/assets/110621233/dd0c6a66-ca3c-4e4a-9406-89248326a3e5" />
      <SForm>
        <span>
          <input type="text" id="text" placeholder="아이디(이메일 형식)" />
        </span>
        <span>
          <input type="text" id="text" placeholder="닉네임" />
        </span>
        <span>
          <input type="text" id="text" placeholder="비밀번호" />
        </span>
        <span>
          <input type="text" id="text" placeholder="비밀번호 확인" />
        </span>
        <span className="checkbox">
          <label htmlFor="male">남자</label>
          <input type="checkbox" id="male" />
          <label htmlFor="female">여자</label>
          <input type="checkbox" id="female" />
        </span>
        <span>
          <input type="date" id="date" />
        </span>
      </SForm>
    </SignUpWrapper>
  );
};

export default SignUpPage;
