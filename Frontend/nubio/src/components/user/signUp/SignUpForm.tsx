import {
  ManIcon,
  Form,
  WomanIcon,
  GenderWrapper,
  SubmitButton,
} from "../../../styles/SSignUpPage";
import { useRef, MouseEvent, useState } from "react";
import useInput from "../../../hooks/useInput";
import { useSelector } from "react-redux";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const SignUpForm = () => {
  const [email, onChangeEmail] = useInput('');
  const [pwd, onChangePwd] = useInput('');
  const [pwdc, onChangePwdc] = useInput('');
  const [nickName, onChangeNickName] = useInput('');
  const [birth, onChangeBirth] = useInput('');
  const [gender, setGender] = useState<string>('MALE');
  const taste = useSelector((state: any) => state.signup.taste);
  const navigate = useNavigate();

  const data = {
    email,
    password: pwd,
    passwordCheck: pwdc,
    nickName,
    birth,
    gender,
    taste,
  }

  const signUp = async (e: any) => {
    e.preventDefault();
    await axios.post('/api/v1/member/signup', data)
                .then((res) => {
                  const { accessToken } = res.data;

                  // axios 기본 header 값 설정
                  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
                  
                  // refresh-token, refresh-token-expiration-time loacalStorage 저장
                  localStorage.setItem('refresh_token', res.data.refresh_token);
                  localStorage.setItem('refresh_token_expiration_time', res.data.refresh_token_expiration_time);
                  navigate('/');
                })
                .catch((err) => {
                  console.log(err);
                })
  }

  // 남자 아이콘
  const manUrl = process.env.PUBLIC_URL + "/assets/man.png";
  const [manCheck, setManCheck] = useState<boolean>(true);
  const manInputRef = useRef<HTMLInputElement | null>(null);
  const manId = manCheck ? "manCheck" : "manUncheck";
  const handleManIconClick = (event: MouseEvent<HTMLImageElement>) => {
    if (manInputRef.current) {
      manInputRef.current.click();
      setManCheck(true);
      setWomanCheck(false);
      setGender('MALE')
    }
  };

  // 여자 아이콘
  const womanUrl = process.env.PUBLIC_URL + "/assets/woman.png";
  const [womanCheck, setWomanCheck] = useState<boolean>(false);
  const womanInputRef = useRef<HTMLInputElement | null>(null);
  const womanId = womanCheck ? "womanCheck" : "womanUncheck";
  const handleWomanIconClick = (event: MouseEvent<HTMLImageElement>) => {
    if (womanInputRef.current) {
      womanInputRef.current.click();
      setWomanCheck(true);
      setManCheck(false);
      setGender("FEMALE");
    }
  };
  return (
    <Form>
      <span>
        <input type="email" placeholder="아이디(이메일 형식)" value={email} onChange={onChangeEmail} />
        <button id="check">중복확인</button>
      </span>
      <span id="nickname">
        <input type="text" placeholder="닉네임" value={nickName} onChange={onChangeNickName} />
        <button id="check">중복확인</button>
      </span>
      <span>
        <input type="password" placeholder="비밀번호" value={pwd} onChange={onChangePwd} />
      </span>
      <span>
        <input type="password" placeholder="비밀번호 확인" value={pwdc} onChange={onChangePwdc} />
      </span>
      <span>
        <input type="date" id="date" value={birth} onChange={onChangeBirth} />
      </span>
      <GenderWrapper>
        <ManIcon src={manUrl} onClick={handleManIconClick} id={manId} />
        <input type="checkbox" id="man" ref={manInputRef} />
        <WomanIcon src={womanUrl} onClick={handleWomanIconClick} id={womanId} />
        <input type="checkbox" id="woman" ref={womanInputRef} />
      </GenderWrapper>

      <SubmitButton type="submit" onClick={signUp}>회 원 가 입</SubmitButton>
    </Form>
  );
};

export default SignUpForm;
