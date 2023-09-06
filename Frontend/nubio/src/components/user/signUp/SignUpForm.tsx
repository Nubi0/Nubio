import {
  ManIcon,
  Form,
  WomanIcon,
  GenderWrapper,
  SubmitButton,
  CheckUserWrapper,
} from "../../../styles/SSignUpPage";
import { useRef, MouseEvent, useState } from "react";

const SignUpForm = () => {
  // 남자 아이콘
  const manUrl = process.env.REACT_APP_MAN_URL;
  const [manCheck, setManCheck] = useState(false);
  const manInputRef = useRef<HTMLInputElement | null>(null);
  const manId = manCheck ? "manCheck" : "manUncheck";
  const handleManIconClick = (event: MouseEvent<HTMLImageElement>) => {
    if (manInputRef.current) {
      manInputRef.current.click();
      setManCheck(!manCheck);
      setWomanCheck(false);
    }
  };

  // 여자 아이콘
  const womanUrl = process.env.REACT_APP_WOMAN_URL;
  const [womanCheck, setWomanCheck] = useState(false);
  const womanInputRef = useRef<HTMLInputElement | null>(null);
  const womanId = womanCheck ? "womanCheck" : "womanUncheck";
  const handleWomanIconClick = (event: MouseEvent<HTMLImageElement>) => {
    if (womanInputRef.current) {
      womanInputRef.current.click();
      setWomanCheck(!womanCheck);
      setManCheck(false);
    }
  };
  return (
    <Form>
      <span>
        <input type="text" placeholder="아이디(이메일 형식)" />
        <button id="check">중복확인</button>
      </span>
      <span id="nickname">
        <input type="text" placeholder="닉네임" />
        <button id="check">중복확인</button>
      </span>
      <span>
        <input type="text" placeholder="비밀번호" />
      </span>
      <span>
        <input type="text" placeholder="비밀번호 확인" />
      </span>
      <span>
        <input type="date" id="date" />
      </span>
      <GenderWrapper>
        <ManIcon src={manUrl} onClick={handleManIconClick} id={manId} />
        <input type="checkbox" id="man" ref={manInputRef} />
        <WomanIcon src={womanUrl} onClick={handleWomanIconClick} id={womanId} />
        <input type="checkbox" id="woman" ref={womanInputRef} />
      </GenderWrapper>
      <SubmitButton>회 원 가 입</SubmitButton>
      <CheckUserWrapper>
        <a href="">이미 회원이신가요?</a>
      </CheckUserWrapper>
    </Form>
  );
};

export default SignUpForm;
